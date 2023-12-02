package com.assetmanager.app.model.entity;

public enum AssigneeType {
    PART_TIME("Part-time"),
    FULL_TIME("Full-time"),
    SEASONAL("Seasonal"),
    TEMPORARY("Temporary");
    private final String name;

    AssigneeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

