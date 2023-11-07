package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.database.Database;
import com.assetmanager.util.security.PasswordEncoderI;
import com.assetmanager.util.security.PasswordEncoder;

import java.io.Serializable;

public class UserBean implements UserBeanI, Serializable {
    Database database = Database.getDatabaseInstance();
    PasswordEncoderI passwordEncoder = new PasswordEncoder();

    @Override
    public Boolean registerUser(User user) {
        System.out.println("User object received for registration");

        if (user.getPassword().equals(user.getConfirmPassword())) {
            String hashedPassword = passwordEncoder.encodePassword(user.getPassword());
            database.getUsersList().add(new User(user.getUsername(), hashedPassword));
            return true;
        }
        return false;
    }

    @Override
    public User findUserByUsername() {
        return null;
    }

}
