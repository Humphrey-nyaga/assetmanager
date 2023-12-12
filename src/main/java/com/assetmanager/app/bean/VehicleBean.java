package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Category;
import com.assetmanager.app.model.entity.vehicle.Vehicle;
import com.assetmanager.util.SerialIDGenerator.SerialIDGenerator;
import com.assetmanager.util.idgenerator.GenericIDGenerator;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Remote
@Stateless

public class VehicleBean extends GenericBean<Vehicle> implements VehicleBeanI {
    @PersistenceContext
    EntityManager em;

    @Inject
    @Named("VehicleID")
    SerialIDGenerator serialIDGenerator;
    @Override
    public Vehicle addOrUpdate(Vehicle vehicle) {
        vehicle.setSerialNumber(serialIDGenerator.generate());
        vehicle.setCategory(Category.VEHICLE);
        return getDao().addOrUpdate(vehicle);
    }

    @Override
    public Vehicle getVehicleBySerialNumber(String serialNumber) {
        return em.createQuery("FROM Vehicle t WHERE serialNumber=:serialNumber", Vehicle.class)
                .setParameter("serialNumber",serialNumber).getSingleResult();
    }
}
