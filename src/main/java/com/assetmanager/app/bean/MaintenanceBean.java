package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Maintenance;
import com.assetmanager.database.Database;

import java.util.List;

public class MaintenanceBean implements MaintenanceBeanI{
    Database database = Database.getDatabaseInstance();
    @Override
    public Boolean create(Maintenance maintenance) {
        return null;
    }

    @Override
    public void delete(Maintenance maintenance) {

    }

    @Override
    public List<Maintenance> list() {
        return database.getMaintenanceList();
    }
}
