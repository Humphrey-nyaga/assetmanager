package com.assetmanager.app.model.entity.vehicle;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.view.html.AssetCreationCard;
import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@HtmlForm(label = "Vehicle", url = "./vehicle")
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
    @HtmlFormField(label = "Vehicle Type", isRequired = true)
    private VehicleType vehicleType;

    @Column(name = "cc",nullable = false)
    @NotNull
    @HtmlFormField(label = "CC", isRequired = true)
    private int cc;

    @Column(name = "engine_type",nullable = false)
    @HtmlFormField(label = "Engine Type", isRequired = true)
    private EngineType engineType;

    @Column(name = "model",nullable = false)
    @NotNull
    @HtmlFormField(label = "Vehicle Model", isRequired = true)
    private String model;

    @Column(name = "year",nullable = false)
    @NotNull
    @HtmlFormField(label = "Year Manufactured", isRequired = true)
    private int year;

    @Column(name = "manufacturer",nullable = false)
    @NotNull
    @HtmlFormField(label = "Manufacturer", isRequired = true)
    private String manufacturer;

    @Column(name = "color",nullable = false)
    @HtmlFormField(label = "Color", isRequired = true)
    private String color;

    @Column(name = "transmission",nullable = false)
    @HtmlFormField(label = "Transmission", isRequired = true)
    private Transmission transmission;

    @Column(name = "number_plate",nullable = false)
    @NotNull
    @HtmlFormField(label = "Number Plate", isRequired = true)
    private String numberPlate;


    @Column(name = "tyre_number",nullable = false)
    @HtmlFormField(label = "Tyre Number", isRequired = true)
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

