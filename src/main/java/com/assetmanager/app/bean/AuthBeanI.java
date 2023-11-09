package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;

public interface AuthBeanI {
    boolean authenticate(User user);
}
