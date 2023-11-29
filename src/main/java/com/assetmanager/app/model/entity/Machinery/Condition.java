package com.assetmanager.app.model.entity.Machinery;

public enum Condition {
    NEW("New"),
    USED("Used"),
    REFURBISHED("Refurbished");

    private String name;

    Condition(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
