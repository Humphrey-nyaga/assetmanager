package com.assetmanager.app.model.entity.Machinery;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.view.html.AssetCreationCard;
import com.assetmanager.app.view.html.HtmlFormField;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "machinery",
        indexes = {
                @Index(name = "idx_name", columnList = "name"),
                @Index(name = "idx_manufacturer", columnList = "manufacturer")
        })
@AssetCreationCard(label = "Machinery",addUrl = "./machinery?action=add")
public class Machinery  extends Asset {

    @Column(name = "year_of_production")
    @HtmlFormField(label = "Year Produced", isRequired = true)
    private int yearOfProduction;

    @Column(name = "model")
    @HtmlFormField(label = "Model", isRequired = true)
    private String model;

    @Column(name = "manufacturer")
    @HtmlFormField(label = "Manufacturer", isRequired = true)
    private String manufacturer;

    @Column(name = "max_load_capacity")
    @HtmlFormField(label = "Max Load", isRequired = true)
    @Positive
    private double maxLoadCapacity;

    @Column(name = "power")
    @HtmlFormField(label = "Power", isRequired = true)
    @Positive
    private double power;

    @Column(name = "expected_lifespan")
    @HtmlFormField(label = "Expected Lifespan", isRequired = true)
    @Positive
    private int expectedLifespan;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_condition")
    @HtmlFormField(label = "Condition", isRequired = true)
    private Condition currentCondition;

    @Enumerated(EnumType.STRING)
    @Column(name = "operational_status")
    @HtmlFormField(label = "Operation Status", isRequired = true)
    private OperationalStatus operationalStatus;
    
    
    public Machinery() {
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    public double getPower() {
        return power;
    }

    public int getExpectedLifespan() {
        return expectedLifespan;
    }

    public Condition getCurrentCondition() {
        return currentCondition;
    }

    public OperationalStatus getOperationalStatus() {
        return operationalStatus;
    }
}