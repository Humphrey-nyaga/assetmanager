package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;

public interface UserBeanI {
    Boolean registerUser(User user);
    User findUserByUsername();
}
