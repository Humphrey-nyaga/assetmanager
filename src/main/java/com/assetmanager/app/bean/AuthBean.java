package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.app.model.entity.UserRole;
import com.assetmanager.database.Database;
import com.assetmanager.database.MysqlDatabase;
import com.assetmanager.exceptions.UserPasswordEncodingException;
import com.assetmanager.util.security.PasswordEncoder;
import com.assetmanager.util.security.PasswordEncoderI;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
@Remote
public class AuthBean implements AuthBeanI, Serializable {
    @EJB
    PasswordEncoderI passwordEncoder;
    @EJB
    MysqlDatabase database;

    @Override
    public User authenticate(User userToAuthenticate) {

        try {
            String hashedPassword = passwordEncoder.encodePassword(userToAuthenticate.getPassword());
            PreparedStatement pre = database.getConnection()
                    .prepareStatement("select id,username,role from users where username=? and password=? limit 1");
            pre.setString(1, userToAuthenticate.getUsername());
            pre.setString(2, hashedPassword);

            ResultSet result = pre.executeQuery();

            User user = new User();

            if (result.next()) {
                user.setId(result.getLong("id"));
                user.setUsername(result.getString("username"));
                user.setUserRole(UserRole.valueOf(result.getString("role")));
            }

            return user;

        } catch (NoSuchAlgorithmException | SQLException e) {
            throw new UserPasswordEncodingException("Password decoding algorithm failed: " + e.getMessage());
        }

    }
}

