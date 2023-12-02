package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.vehicle.Vehicle;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class VehicleBean extends GenericBean<Vehicle> implements VehicleBeanI{
}
