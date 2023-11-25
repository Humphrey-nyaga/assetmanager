package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;

import javax.ejb.Remote;

public interface UserBeanI extends GenericBeanI<User> {
    Boolean registerUser(User user);
    User findUserByUsername();
}
