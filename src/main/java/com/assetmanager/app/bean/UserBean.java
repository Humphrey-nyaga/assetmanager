package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.database.Database;
import com.assetmanager.util.logger.FileLogger;
import com.assetmanager.util.security.PasswordEncoderI;
import com.assetmanager.util.security.PasswordEncoder;

import java.io.Serializable;
import java.util.logging.Logger;


public class UserBean implements UserBeanI, Serializable {
    private static final Logger LOGGER = FileLogger.getLogger();

    Database database = Database.getDatabaseInstance();
    PasswordEncoderI passwordEncoder = new PasswordEncoder();

    @Override
    public Boolean registerUser(User user) {

        if (user.getPassword().equals(user.getConfirmPassword())) {
            String hashedPassword = passwordEncoder.encodePassword(user.getPassword());
            database.getUsersList().add(new User(user.getUsername(), hashedPassword));
            LOGGER.info("User Registered Successfully");
            return true;
        }
        LOGGER.warning("User Registration failed");
        return false;
    }

    @Override
    public User findUserByUsername() {
        return null;
    }

}
