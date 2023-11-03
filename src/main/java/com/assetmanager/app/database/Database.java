package com.assetmanager.app.database;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Database implements Serializable {
    private Database databaseInstance;
    private LocalDateTime dateTimeCreatedAt;

    private Database() {
    }

    public Database getDatabaseInstance() {
        if (databaseInstance == null) {
            databaseInstance = new Database();
            databaseInstance.setDateTimeCreatedAt(LocalDateTime.now());
            System.out.println("Database created at: " + getDateTimeCreatedAt());
        }
        return databaseInstance;
    }

    private void setDateTimeCreatedAt(LocalDateTime dateTimeCreatedAt) {
        this.dateTimeCreatedAt = dateTimeCreatedAt;
    }

    public LocalDateTime getDateTimeCreatedAt() {
        return dateTimeCreatedAt;
    }
}
