package com.assetmanager.app.model.entity;

import java.io.Serializable;

public enum RequestStatus implements Serializable {
    COMPLETED("Completed"),
    PENDING("Pending"),
    REJECTED("Rejected"),
    APPROVED("Approved");

    private final String name;
    RequestStatus(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
