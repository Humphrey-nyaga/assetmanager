package com.assetmanager.database;

import com.assetmanager.app.model.entity.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {
    private final List<User> usersList = new ArrayList<>();
    private final List<Asset> assetList = new ArrayList<>();
    private final List<Assignee> assigneeList = new ArrayList<>();
    private final List<AssetRequest> assetRequestsList = new ArrayList<>();
    private final List<Maintenance> maintenanceList = new ArrayList<>();

    private static Database databaseInstance;
    private LocalDateTime dateTimeCreatedAt;

    private Database() {
    }

    public static Database getDatabaseInstance() {
        if (databaseInstance == null) {
            databaseInstance = new Database();
            databaseInstance.setDateTimeCreatedAt(LocalDateTime.now());
            System.out.println("Database created at: " + databaseInstance.getDateTimeCreatedAt());
        }
        return databaseInstance;
    }

    private void setDateTimeCreatedAt(LocalDateTime dateTimeCreatedAt) {
        this.dateTimeCreatedAt = dateTimeCreatedAt;
    }

    public LocalDateTime getDateTimeCreatedAt() {
        return dateTimeCreatedAt;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public List<Asset> getAssetList() {
        return assetList;
    }

    public List<Assignee> getAssigneeList() {
        return assigneeList;
    }

    public List<AssetRequest> getAssetRequestsList() {
        return assetRequestsList;
    }

    public List<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }
}
