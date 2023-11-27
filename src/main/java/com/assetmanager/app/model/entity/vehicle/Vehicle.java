package com.assetmanager.app.model.entity.vehicle;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.database.helper.NotNull;

@DbTable(name = "vehicles")
public class Vehicle extends Asset {

    @DbColumn(name = "car_type")
    @NotNull
    private String carType;

    @DbColumn(name = "cc")
    @NotNull
    private int cc;

    @DbColumn(name = "engine_type")
    private EngineType engineType;

    @DbColumn(name = "model")
    @NotNull
    private String model;

    @DbColumn(name = "year")
    @NotNull
    private int year;

    @DbColumn(name = "manufacturer")
    @NotNull
    private String manufacturer;

    @DbColumn(name = "color")
    private String color;

    @DbColumn(name = "transmission")
    private Transmission transmission;

    @DbColumn(name = "fuel_type")
    private FuelType fuelType;

    @DbColumn(name = "number_plate")
    @NotNull
    private String numberPlate;

    @DbColumn(name = "chassis_number")
    @NotNull
    private String chassisNumber;

    @DbColumn(name = "tyre_number")
    private String tyreNumber;

    public Vehicle() {
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
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

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
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
}

