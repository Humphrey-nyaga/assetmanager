package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;

import java.io.Serializable;

public interface AuthBeanI extends Serializable {
    boolean authenticate(User user);
}
