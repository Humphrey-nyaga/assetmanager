package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.app.view.html.HtmlComponent;
import com.assetmanager.database.Database;
import com.assetmanager.database.MysqlDatabase;
import com.assetmanager.exceptions.UserPasswordEncodingException;
import com.assetmanager.util.logger.FileLogger;
import com.assetmanager.util.security.PasswordEncoderI;
import com.assetmanager.util.security.PasswordEncoder;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


public class UserBean extends  GenericBean<User> implements UserBeanI, Serializable {
    private static final Logger LOGGER = FileLogger.getLogger();

    Connection conn = MysqlDatabase.getDatabaseInstance().getConnection();

    PasswordEncoderI passwordEncoder = new PasswordEncoder();

    @Override
    public Boolean registerUser(User user) {

        if (user.getPassword().equals(user.getConfirmPassword())) {
            String hashedPassword = null;
            try {
                hashedPassword = passwordEncoder.encodePassword(user.getPassword());
                PreparedStatement registerUserStmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?,?);");
                registerUserStmt.setString(1, user.getUsername());
                registerUserStmt.setString(2, hashedPassword);
                registerUserStmt.execute();
                LOGGER.info("User Registered Successfully");
                return true;
            } catch (NoSuchAlgorithmException e) {
                throw new UserPasswordEncodingException("Password encoding algorithm failed: " + e.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
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
