package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.app.view.html.HtmlComponent;
import com.assetmanager.database.Database;
import com.assetmanager.database.MysqlDatabase;
import com.assetmanager.exceptions.UserPasswordEncodingException;
import com.assetmanager.util.logger.FileLogger;
import com.assetmanager.util.security.PasswordEncoderI;
import com.assetmanager.util.security.PasswordEncoder;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Remote
public class UserBean extends GenericBean<User> implements UserBeanI, Serializable {

    @EJB
    MysqlDatabase database;
    @EJB
    PasswordEncoderI passwordEncoder;

    @Override
    public Boolean registerUser(User user) {

        if (user.getPassword().equals(user.getConfirmPassword())) {
            String hashedPassword = null;
            try {
                Connection conn = database.getConnection();
                hashedPassword = passwordEncoder.encodePassword(user.getPassword());
                PreparedStatement registerUserStmt = conn.prepareStatement("INSERT INTO users (username, password,role) VALUES (?,?,?);");
                registerUserStmt.setString(1, user.getUsername());
                registerUserStmt.setString(2, hashedPassword);
                registerUserStmt.setString(3, String.valueOf(user.getUserRole()));
                registerUserStmt.execute();
                System.out.println("User Registered Successfully");
                return true;
            } catch (NoSuchAlgorithmException e) {
                throw new UserPasswordEncodingException("Password encoding algorithm failed: " + e.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("User Registration failed");
        return false;
    }

    @Override
    public User findUserByUsername() {
        return null;
    }


}
