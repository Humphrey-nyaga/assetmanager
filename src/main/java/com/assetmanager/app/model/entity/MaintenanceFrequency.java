package com.assetmanager.app.model.entity;

import lombok.Getter;

@Getter
public enum MaintenanceFrequency {
    WEEKLY("Weekly"),
    BI_WEEKLY("Bi-Weekly"),
    MONTHLY("Monthly"),
    QUARTERLY("Quarterly");

    private final String name;
     MaintenanceFrequency(String name){
        this.name = name;
    }

}
