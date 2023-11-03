package com.assetmanager.app.bean;

import com.assetmanager.app.model.User;

import java.io.Serializable;

public interface UserBeanI {
    User registerUser(String username, String password);
    User findUserByUsername();

}
