package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.exceptions.UserAlreadyExistsException;
import com.assetmanager.exceptions.UserPasswordEncodingException;
import com.assetmanager.util.security.PasswordEncoderI;


import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Stateless
@Remote
public class UserBean extends GenericBean<User> implements UserBeanI, Serializable {

    @Inject
    PasswordEncoderI passwordEncoder;

    @Override
    public Boolean registerUser(User user) {
        try {
            if (findUserByUsername(user.getUsername()).isPresent())
                throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists.");

            if (user.getPassword().equals(user.getConfirmPassword())) {
                user.setPassword(passwordEncoder.encodePassword(user.getPassword()));
                getDao().create(user);
                return true;

            }
        } catch (NoSuchAlgorithmException e) {
            throw new UserPasswordEncodingException("Password encoding algorithm failed: " + e.getMessage());
        } catch (UserAlreadyExistsException e) {
            System.out.println("User Registration Error " +e.getMessage());
        }
        System.out.println("User Registration failed");
        return false;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        List<User> users = list(new User());
        return users.stream().filter(user -> user.getUsername().equals(username))
                .findFirst();
    }


}
