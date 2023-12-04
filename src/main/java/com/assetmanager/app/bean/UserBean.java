package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.app.observer.Created;
import com.assetmanager.exceptions.InvalidEmailFormatException;
import com.assetmanager.exceptions.UserAlreadyExistsException;
import com.assetmanager.exceptions.UserPasswordEncodingException;
import com.assetmanager.util.EmailValidator;
import com.assetmanager.util.security.PasswordEncoderI;


import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Stateless
@Local
public class UserBean extends GenericBean<User> implements UserBeanI, Serializable {

    @Inject
    PasswordEncoderI passwordEncoder;
    @Inject
    EmailValidator emailValidator;
    @Inject
    @Created
    Event<User> userEvent;


    @Override
    public Boolean registerUser(User user) {
        try {
            if (findUserByUsername(user.getUsername()).isPresent())
                throw new UserAlreadyExistsException("Failed!!. User with username " + user.getUsername() + " already exists.");

            if (findUserByEmail(user.getEmail()).isPresent())
                throw new UserAlreadyExistsException("Failed!!. User with email " + user.getEmail() + " already exists.");

            if (!emailValidator.isValidEmail(user.getEmail()))
                throw new InvalidEmailFormatException("Failed!!. Invalid Email Format");

            if (user.getPassword().equals(user.getConfirmPassword())) {
                user.setPassword(passwordEncoder.encodePassword(user.getPassword()));

                getDao().addOrUpdate(user);

                userEvent.fire(user);

                return true;

            }
        } catch (NoSuchAlgorithmException e) {
            throw new UserPasswordEncodingException("Password encoding algorithm failed: " + e.getMessage());
        } catch (UserAlreadyExistsException e) {
            System.out.println("User Registration Error " + e.getMessage());
        } catch (InvalidEmailFormatException ex) {
            System.out.println("Email Verification Error: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        List<User> users = list(new User());
        return users.stream().filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        List<User> users = list(new User());
        return users.stream().filter(user -> user.getEmail().equals(email))
                .findFirst();
    }


}
