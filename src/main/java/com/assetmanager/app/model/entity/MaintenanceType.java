package com.assetmanager.app.model.entity;

public enum MaintenanceType {
    INSPECTION("Inspection"),
    LUBRICATION("Lubrication"),
    ALIGNMENT("Alignment"),
    FLUID_CHANGE("Fluid-Change"),
    CALIBRATION("Calibration"),
    ELECTRICAL("Electrical System Repair"),
    PARTS_REPLACEMENT("Parts-Replacement");

    private final String name;
    MaintenanceType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
