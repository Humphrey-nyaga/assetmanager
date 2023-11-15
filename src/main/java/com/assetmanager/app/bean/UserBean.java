package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.app.view.html.HtmlComponent;
import com.assetmanager.database.Database;
import com.assetmanager.exceptions.UserPasswordEncodingFailed;
import com.assetmanager.util.logger.FileLogger;
import com.assetmanager.util.security.PasswordEncoderI;
import com.assetmanager.util.security.PasswordEncoder;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;


public class UserBean implements UserBeanI, Serializable {
    private static final Logger LOGGER = FileLogger.getLogger();

    Database database = Database.getDatabaseInstance();
    PasswordEncoderI passwordEncoder = new PasswordEncoder();

    @Override
    public Boolean registerUser(User user) {

        if (user.getPassword().equals(user.getConfirmPassword())) {
            String hashedPassword = null;
            try {
                hashedPassword = passwordEncoder.encodePassword(user.getPassword());
                database.getUsersList().add(new User(user.getUsername(), hashedPassword));
                LOGGER.info("User Registered Successfully");
                return true;
            } catch (NoSuchAlgorithmException e) {
                throw new UserPasswordEncodingFailed("Password encoding algorithm failed: " + e.getMessage());
            }
        }
        LOGGER.warning("User Registration failed");
        return false;
    }

    @Override
    public User findUserByUsername() {
        return null;
    }
    @Override
    public String getAllUsers() {
        Database database = Database.getDatabaseInstance();
        List<User> users = database.getUsersList();
        LOGGER.info("Retrieving All users");
        return HtmlComponent.table(users, User.class);
    }

}
