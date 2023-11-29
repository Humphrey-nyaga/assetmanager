package com.assetmanager.app.model.entity;

import java.io.Serializable;

public enum Category implements Serializable {
    ELECTRONICS("Electronics"),
    SOFTWARE("Software"),
    DIGITAL("Digital"),
    VEHICLE("Vehicle"),
    ART("Art"),
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

