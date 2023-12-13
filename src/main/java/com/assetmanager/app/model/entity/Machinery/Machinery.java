package com.assetmanager.app.model.entity.Machinery;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Maintenance;
import com.assetmanager.app.model.entity.MaintenanceFrequency;
import com.assetmanager.app.view.html.*;

import com.assetmanager.util.YearConverter;
import com.assetmanager.util.idgenerator.IdPrefix;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.YearDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter

@Table(name = "machinery")
@AssetCreationCard(label = "Machinery", servletUrl = "./machinery")
@HtmlForm(label = "Machinery", url = "./machinery")
@IdPrefix(prefix = "ASN-MCH000")
@HtmlTable(name = "Machinery Table", label = "Machinery", url = "./machinery", addUrl = "./machinery?action=add")

public class Machinery  extends Asset {


    @Column(name = "year_of_production",nullable = false)
    @HtmlFormField(label = "Year Produced", isRequired = true)
    @Convert(converter = YearConverter.class)
    @JsonSerialize(using = YearSerializer.class)
    @JsonDeserialize(using = YearDeserializer.class)
    @JsonFormat(pattern = "yyyy")
    private Year yearOfProduction;


    @Column(name = "model",nullable = false)
    @HtmlFormField(label = "Model", isRequired = true)
    @TableColumnHeader(header = "Model",sortable = true)
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
    @TableColumnHeader(header = "Condition" ,sortable = true)
    private Condition currentCondition;


    @Enumerated(EnumType.STRING)
    @Column(name = "operational_status",nullable = false)
    @HtmlFormField(label = "Operation Status", isRequired = true)
    @TableColumnHeader(header = "Status")
    private OperationalStatus operationalStatus;


    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_frequency",nullable = false)
    @HtmlFormField(label = "Maintenance Frequency", isRequired = true)
    private MaintenanceFrequency maintenanceFrequency;
    
    @OneToMany(mappedBy = "machinery")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Maintenance> machineryMaintenance = new ArrayList<>();
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