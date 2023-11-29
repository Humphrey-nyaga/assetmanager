package com.assetmanager.app.model.entity.Machinery;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.view.html.AssetCreationCard;

import javax.persistence.*;

@Entity
@Table(name = "machinery",
        indexes = {
                @Index(name = "idx_name", columnList = "name"),
                @Index(name = "idx_manufacturer", columnList = "manufacturer")
        })
@AssetCreationCard(label = "Machinery",addUrl = "./machinery?action=add")
public class Machinery  extends Asset {

    @Column(name = "year_of_production")
    private int yearOfProduction;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "max_load_capacity")
    private double maxLoadCapacity;

    @Column(name = "power")
    private double power;

    @Column(name = "expected_lifespan")
    private int expectedLifespan;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_condition")
    private Condition currentCondition;

    @Enumerated(EnumType.STRING)
    @Column(name = "operational_status")
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