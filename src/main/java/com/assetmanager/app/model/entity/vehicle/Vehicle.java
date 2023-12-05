package com.assetmanager.app.model.entity.vehicle;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.view.html.AssetCreationCard;
import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.util.YearConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.Year;

@Getter
@Entity
@Setter
@HtmlForm(label = "Vehicle", url = "./vehicle")
@Table(name = "vehicle")
@AssetCreationCard(label = "Vehicle",addUrl = "./vehicle?action=add")
public class Vehicle extends Asset {

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type",nullable = false)
    @HtmlFormField(label = "Vehicle Type", isRequired = true)
    private VehicleType vehicleType;

    @Column(name = "cc",nullable = false)
    @HtmlFormField(label = "CC ", isRequired = true)
    @Positive
    private Integer cc;

    @Column(name = "engine_type",nullable = false)
    @HtmlFormField(label = "Engine Type", isRequired = true)
    private EngineType engineType;

    @Column(name = "model",nullable = false)
    @HtmlFormField(label = "Vehicle Model", isRequired = true)
    private String model;

    @Column(name = "year",nullable = false)
    @HtmlFormField(label = "Year Manufactured", isRequired = true)
    @Convert(converter = YearConverter.class)
    private Year year;

    @Column(name = "manufacturer",nullable = false)
    @HtmlFormField(label = "Manufacturer", isRequired = true)
    private String manufacturer;

    @Column(name = "color",nullable = false)
    @HtmlFormField(label = "Color", isRequired = true)
    private String color;

    @Column(name = "transmission",nullable = false)
    @HtmlFormField(label = "Transmission", isRequired = true)
    private Transmission transmission;

    @Column(name = "number_plate",nullable = false)
    @HtmlFormField(label = "Number Plate", isRequired = true)
    private String numberPlate;


    @Column(name = "tyre_number",nullable = false)
    @HtmlFormField(label = "Tyre Number", isRequired = true)
    @Positive
    private Integer tyreNumber;

    public Vehicle() {
    }

}

