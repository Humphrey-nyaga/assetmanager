package com.assetmanager.database;

import com.assetmanager.app.model.Asset;
import com.assetmanager.app.model.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {
    private List<User> usersList = new ArrayList<>();
   private List<Asset> assetList  = new ArrayList<>();
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
}
