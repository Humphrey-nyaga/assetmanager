package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;

import javax.ejb.Remote;

public interface AuthBeanI {
    User authenticate(User user);
}
