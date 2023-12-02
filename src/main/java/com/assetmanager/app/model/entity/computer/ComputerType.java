package com.assetmanager.app.model.entity.computer;

public enum ComputerType {
    DESKTOP("Desktop"),
    MONITOR("Monitor"),
    SERVER("Server"),
    LAPTOP("Laptop"),
    TABLET("Tablet"),
    SMARTPHONE("Smartphone");

    private String name;
    ComputerType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
