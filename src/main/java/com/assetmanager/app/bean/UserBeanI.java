package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;

import java.util.Optional;

public interface UserBeanI extends GenericBeanI<User> {
    Boolean registerUser(User user);
    Optional<User> findUserByUsername(String username);
}
