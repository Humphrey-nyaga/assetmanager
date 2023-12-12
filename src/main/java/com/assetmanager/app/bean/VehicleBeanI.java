package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.vehicle.Vehicle;


public interface VehicleBeanI extends GenericBeanI<Vehicle> {
    Vehicle getVehicleBySerialNumber(String serialNumber);
}
