package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.database.Database;
import com.assetmanager.exceptions.UserPasswordEncodingFailed;
import com.assetmanager.util.security.PasswordEncoder;
import com.assetmanager.util.security.PasswordEncoderI;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class AuthBean implements AuthBeanI, Serializable {
    Database database = Database.getDatabaseInstance();
    PasswordEncoderI passwordEncoder = new PasswordEncoder();

    @Override
    public boolean authenticate(User userToAuthenticate) {
        for (User user : database.getUsersList()) {
            try {
                if (userToAuthenticate.getUsername().equals(user.getUsername())
                        && passwordEncoder.verifyPassword(userToAuthenticate.getPassword(), user.getPassword())) {
                    return true;
                }
            } catch (NoSuchAlgorithmException e) {
                throw new UserPasswordEncodingFailed("Password encoding algorithm failed: " + e.getMessage());
            }

        }

        return false;
    }
}
