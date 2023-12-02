package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Maintenance;
import com.assetmanager.app.scheduler.maintenance.MaintenanceScheduler;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote
public class MaintenanceBean extends GenericBean<Maintenance> implements MaintenanceBeanI{
   @Inject
    MaintenanceScheduler maintenanceScheduler;
    @Override
    public void create(Maintenance maintenance) {
        getDao().create(maintenance);
    }
}
