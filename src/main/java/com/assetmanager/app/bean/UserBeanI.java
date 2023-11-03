package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;

public interface UserBeanI {
    User registerUser(String username, String password);
    User findUserByUsername();

}
