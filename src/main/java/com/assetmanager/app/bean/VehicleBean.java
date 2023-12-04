package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.vehicle.Vehicle;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote
@Stateless

public class VehicleBean extends GenericBean<Vehicle> implements VehicleBeanI {
}
