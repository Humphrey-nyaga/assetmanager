package com.assetmanager.app.model.entity.Machinery;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.MaintenanceFrequency;
import com.assetmanager.app.view.html.AssetCreationCard;
import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.util.idgenerator.IdPrefix;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "machinery",
        indexes = {
                @Index(name = "idx_name", columnList = "name"),
                @Index(name = "idx_manufacturer", columnList = "manufacturer")
        })
@AssetCreationCard(label = "Machinery",addUrl = "./machinery?action=add")
@HtmlForm(label = "Machinery", url = "./machinery")
@Getter @Setter
@DbTable(name = "machinery")
@IdPrefix(prefix = "MCH000")
public class Machinery  extends Asset {

    @Column(name = "year_of_production")
    @HtmlFormField(label = "Year Produced", isRequired = true)
    @DbColumn(name = "year_of_production", definition = "INTEGER")
    private int yearOfProduction;

    @Column(name = "model")
    @HtmlFormField(label = "Model", isRequired = true)
    @DbColumn(name = "model")
    private String model;

    @Column(name = "manufacturer")
    @HtmlFormField(label = "Manufacturer", isRequired = true)
    @DbColumn(name = "manufacturer")
    private String manufacturer;

    @Column(name = "max_load_capacity")
    @HtmlFormField(label = "Max Load", isRequired = true)
    @Positive
    @DbColumn(name = "max_load_capacity", definition = "DOUBLE")
    private double maxLoadCapacity;

    @Column(name = "power")
    @HtmlFormField(label = "Power", isRequired = true)
    @Positive
    @DbColumn(name = "power", definition = "DOUBLE")
    private double power;

    @Column(name = "expected_lifespan")
    @HtmlFormField(label = "Expected Lifespan", isRequired = true)
    @Positive
    @DbColumn(name = "expected_lifespan", definition = "INTEGER")
    private int expectedLifespan;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_condition")
    @HtmlFormField(label = "Condition", isRequired = true)
    @DbColumn(name = "current_condition")
    private Condition currentCondition;

    @Enumerated(EnumType.STRING)
    @Column(name = "operational_status")
    @HtmlFormField(label = "Operation Status", isRequired = true)
    @DbColumn(name = "operational_status")
    private OperationalStatus operationalStatus;


    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_frequency")
    @HtmlFormField(label = "Maintenance Frequency", isRequired = true)
    @DbColumn(name = "maintenance_frequency")
    MaintenanceFrequency maintenanceFrequency;
    
    
    public Machinery() {
    }

}