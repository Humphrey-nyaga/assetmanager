package com.assetmanager.app.model.entity.vehicle;

public enum EngineType {
    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid");

    private final String name;

    EngineType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
