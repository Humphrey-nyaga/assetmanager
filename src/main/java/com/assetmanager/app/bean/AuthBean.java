package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.database.Database;
import com.assetmanager.util.security.PasswordEncoder;
import com.assetmanager.util.security.PasswordEncoderI;
import java.io.Serializable;

public class AuthBean implements AuthBeanI, Serializable {
    Database database = Database.getDatabaseInstance();
    PasswordEncoderI passwordEncoder = new PasswordEncoder();

    @Override
    public boolean authenticate(User userToAuthenticate) {
        for (User user : database.getUsersList()) {
            if (userToAuthenticate.getUsername().equals(user.getUsername())
                    && passwordEncoder.verifyPassword(userToAuthenticate.getPassword(), user.getPassword())) {
                return true;
            }
        }

        return false;
    }
}
