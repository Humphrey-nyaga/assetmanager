package com.assetmanager.app.model.entity.vehicle;


public enum Transmission {
    AUTOMATIC("Automatic"),
    MANUAL("Manual"),
    SEMI_AUTOMATIC("Semi-Automatic"),
    CVT("Continuously Variable Transmission"),
    DCT("Dual Clutch Transmission");

    private final String name;

    Transmission(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return name;
    }
}
