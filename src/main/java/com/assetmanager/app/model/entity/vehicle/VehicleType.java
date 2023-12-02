package com.assetmanager.app.model.entity.vehicle;

public enum VehicleType {
    SUV("SUV"),
    SALOON("Saloon"),
    TRUCK("Truck"),
    PICKUP("Pickup"),
    WAGON("Wagon"),
    VAN("Van");

    private final String getName;

    VehicleType(String displayName) {
        this.getName = displayName;
    }

    public String getName() {
        return getName;
    }
}




