package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Maintenance;
import com.assetmanager.app.scheduler.maintenance.MaintenanceScheduler;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Local
public class MaintenanceBean extends GenericBean<Maintenance> implements MaintenanceBeanI{
   @Inject
    MaintenanceScheduler maintenanceScheduler;
    @Override
    public void addOrUpdate(Maintenance maintenance) {
        getDao().addOrUpdate(maintenance);
    }
}
