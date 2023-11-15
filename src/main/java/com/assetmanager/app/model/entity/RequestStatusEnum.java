package com.assetmanager.app.model.entity;

import java.io.Serializable;

public enum RequestStatusEnum implements Serializable {
    IN_PROGRESS("In Progress"),
    PENDING("Pending"),
    COMPLETED("Completed");

    private final String status;
    RequestStatusEnum(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
