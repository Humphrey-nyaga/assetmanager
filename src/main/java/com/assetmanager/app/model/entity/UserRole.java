package com.assetmanager.app.model.entity;

import java.io.Serializable;

public enum UserRole implements Serializable {
    SUPER("Super user"),
    ADMIN("Admin"),
    REGULAR("Regular");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
