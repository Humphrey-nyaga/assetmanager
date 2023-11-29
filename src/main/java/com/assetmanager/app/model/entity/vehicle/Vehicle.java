package com.assetmanager.app.model.entity.vehicle;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.view.html.AssetCreationCard;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vehicle",
        indexes = {
                @Index(name = "idx_number_plate", columnList = "number_plate"),
                @Index(name = "idx_manufacturer", columnList = "manufacturer"),
                @Index(name = "idx_vehicle_type_transmission", columnList = "vehicle_type, transmission")
        }
)
@AssetCreationCard(label = "Vehicle",addUrl = "./vehicle?action=add")
public class Vehicle extends Asset {
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type",nullable = false)
    private VehicleType vehicleType;

    @Column(name = "cc",nullable = false)
    @NotNull
    private int cc;

    @Column(name = "engine_type",nullable = false)
    private EngineType engineType;

    @Column(name = "model",nullable = false)
    @NotNull
    private String model;

    @Column(name = "year",nullable = false)
    @NotNull
    private int year;

    @Column(name = "manufacturer",nullable = false)
    @NotNull
    private String manufacturer;

    @Column(name = "color",nullable = false)
    private String color;

    @Column(name = "transmission",nullable = false)
    private Transmission transmission;

    @Column(name = "number_plate",nullable = false)
    @NotNull
    private String numberPlate;

    @Column(name = "chassis_number")
    @NotNull
    private String chassisNumber;

    @Column(name = "tyre_number",nullable = false)
    private String tyreNumber;

    public Vehicle() {
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getTyreNumber() {
        return tyreNumber;
    }

    public void setTyreNumber(String tyreNumber) {
        this.tyreNumber = tyreNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}

