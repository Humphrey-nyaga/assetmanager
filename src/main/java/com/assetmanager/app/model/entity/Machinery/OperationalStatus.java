package com.assetmanager.app.model.entity.Machinery;

public enum OperationalStatus {
    OPERATIONAL("In Operation"), MAINTENANCE("Under Maintenance"), OUT_OF_ORDER("Out of order");

    private final String name;

    OperationalStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
