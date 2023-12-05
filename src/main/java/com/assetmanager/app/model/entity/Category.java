package com.assetmanager.app.model.entity;

import java.io.Serializable;

public enum Category implements Serializable {
    ELECTRONICS("Electronics"),
    VEHICLE("Vehicle"),
    MACHINERY("Machinery"),

    LAND("Land"),
    BUILDING("Building");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

