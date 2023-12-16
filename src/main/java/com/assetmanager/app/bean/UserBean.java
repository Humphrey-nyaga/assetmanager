package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.app.observer.Created;
import com.assetmanager.exceptions.InvalidEmailFormatException;
import com.assetmanager.exceptions.UserAlreadyExistsException;
import com.assetmanager.exceptions.UserPasswordEncodingException;
import com.assetmanager.util.EmailValidator;
import com.assetmanager.util.security.PasswordEncoderI;


import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@Stateless
@Remote
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
            if (findUserByUsername(user.getUsername())!=null)
                throw new UserAlreadyExistsException("Failed!!. User with username " + user.getUsername() + " already exists.");

            if (findUserByEmail(user.getEmail())!=null)
                throw new UserAlreadyExistsException("Failed!!. User with email " + user.getEmail() + " already exists.");

//            if (!emailValidator.isValidEmail(user.getEmail()))
//                throw new InvalidEmailFormatException("Failed!!. Invalid Email Format");

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
    public User findUserByUsername(String username) {

        try {
            return em.createQuery("FROM User a WHERE a.username=:username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No user found with the specified username");
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        try {
            return em.createQuery("FROM User a WHERE a.email=:email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No user found with the specified email");
        }
        return null;
    }

}