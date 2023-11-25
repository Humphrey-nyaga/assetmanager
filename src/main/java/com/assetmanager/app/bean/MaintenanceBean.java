package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Maintenance;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class MaintenanceBean extends GenericBean<Maintenance> implements MaintenanceBeanI{
}
