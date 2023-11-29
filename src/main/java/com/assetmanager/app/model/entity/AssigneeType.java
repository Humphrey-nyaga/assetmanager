package com.assetmanager.app.model.entity;

public enum AssigneeType {
    PART_TIME("Part-time"),
    FULL_TIME("Full-time"),
    SEASONAL("Seasonal"),
    TEMPORARY("Temporary");
    private final String displayName;

    AssigneeType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

