package com.assetmanager.app.model.entity.Machinery;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.BaseEntity;
import com.assetmanager.app.model.entity.MaintenanceFrequency;
import com.assetmanager.app.view.html.AssetCreationCard;
import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;

import com.assetmanager.util.YearConverter;
import com.assetmanager.util.idgenerator.IdPrefix;
import com.assetmanager.util.ValidYear;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Year;

@Entity
@Table(name = "machinery")
@AssetCreationCard(label = "Machinery",addUrl = "./machinery?action=add")
@HtmlForm(label = "Machinery", url = "./machinery")
@Getter @Setter
@IdPrefix(prefix = "MCH000")
public class Machinery  extends Asset {

    @Column(name = "year_of_production",nullable = false)
    @HtmlFormField(label = "Year Produced", isRequired = true)
    @Convert(converter = YearConverter.class)
    private Year yearOfProduction;

    @Column(name = "model",nullable = false)
    @HtmlFormField(label = "Model", isRequired = true)
    private String model;

    @Column(name = "manufacturer",nullable = false)
    @HtmlFormField(label = "Manufacturer", isRequired = true)
    private String manufacturer;

    @Column(name = "max_load_capacity",nullable = false)
    @HtmlFormField(label = "Max Load(Tonnes)", isRequired = true)
    @Positive
    private Double maxLoadCapacity;

    @Column(name = "power",nullable = false)
    @HtmlFormField(label = "Power(Kilowatt (kW))", isRequired = true)
    @Positive
    private Double power;

    @Column(name = "expected_lifespan",nullable = false)
    @HtmlFormField(label = "Expected Lifespan(Years)", isRequired = true)
    @Positive
    private Double expectedLifespan;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_condition",nullable = false)
    @HtmlFormField(label = "Condition", isRequired = true)
    private Condition currentCondition;

    @Enumerated(EnumType.STRING)
    @Column(name = "operational_status",nullable = false)
    @HtmlFormField(label = "Operation Status", isRequired = true)
    private OperationalStatus operationalStatus;


    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_frequency",nullable = false)
    @HtmlFormField(label = "Maintenance Frequency", isRequired = true)
    MaintenanceFrequency maintenanceFrequency;
    
    
    public Machinery() {
    }

    @Override
    public String toString() {
        return "Machinery{" +
                "yearOfProduction=" + yearOfProduction +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", maxLoadCapacity=" + maxLoadCapacity +
                ", power=" + power +
                ", expectedLifespan=" + expectedLifespan +
                ", currentCondition=" + currentCondition +
                ", operationalStatus=" + operationalStatus +
                ", maintenanceFrequency=" + maintenanceFrequency +
                '}';
    }
}