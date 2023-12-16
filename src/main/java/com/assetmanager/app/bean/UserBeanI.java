package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;

public interface UserBeanI extends GenericBeanI<User> {
    Boolean registerUser(User user);
    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
