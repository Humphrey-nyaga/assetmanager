package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.util.security.PasswordEncoderI;
import com.assetmanager.util.security.PasswordEncoderImpl;

import java.io.Serializable;

public class UserBean implements UserBeanI, Serializable {
    PasswordEncoderI passwordEncoder = new PasswordEncoderImpl();
    @Override
    public User registerUser(String username, String password) {
        System.out.println("User object received for registration");
        String hashedPassword = passwordEncoder.encodePassword(password);
        return new User(username,hashedPassword);
    }

    @Override
    public User findUserByUsername() {
        return null;
    }


}
