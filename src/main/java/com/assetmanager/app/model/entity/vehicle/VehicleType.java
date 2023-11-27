package com.assetmanager.app.model.entity.vehicle;

public enum VehicleType {
    SUV,
    SALOON,
    TRUCK,
    PICKUP,
    WAGON,
    VAN;

    public static VehicleType fromString(String type) {
        for (VehicleType carType : VehicleType.values()) {
            if (carType.name().equalsIgnoreCase(type)) {
                return carType;
            }
        }
        return null;
    }
}


