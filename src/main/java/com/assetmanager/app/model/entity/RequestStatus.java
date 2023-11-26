package com.assetmanager.app.model.entity;

import java.io.Serializable;

public enum RequestStatus implements Serializable {
    COMPLETED("Completed"),
    PENDING("Pending"),
    REJECTED("Rejected"),
    APPROVED("Approved");

    private final String status;
    RequestStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}