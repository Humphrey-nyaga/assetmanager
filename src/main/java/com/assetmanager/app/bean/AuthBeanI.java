package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface AuthBeanI {
    User authenticate(User user);
}
