package com.assetmanager.app.model.entity.vehicle;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.app.model.entity.Category;
import com.assetmanager.app.model.entity.Maintenance;
import com.assetmanager.app.view.html.*;
import com.assetmanager.util.YearConverter;
import com.assetmanager.util.idgenerator.IdPrefix;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.JSR310DateTimeDeserializerBase;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
@HtmlForm(label = "Vehicle", url = "./vehicle")
@Table(name = "vehicle")
@AssetCreationCard(label = "Vehicle",addUrl = "./vehicle")
@HtmlTable(name = "Vehicles Table", label = "Vehicle", url = "./vehicle", addUrl = "./vehicle?action=add")
public class Vehicle extends Asset {

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type",nullable = false)
    @HtmlFormField(label = "Vehicle Type", isRequired = true)
    @TableColumnHeader(header = "Vehicle Type")
    private VehicleType vehicleType;


    @Column(name = "cc",nullable = false)
    @HtmlFormField(label = "CC ", isRequired = true)
    @TableColumnHeader(header = " Engine cc")
    @Positive
    private Integer cc;

    @Column(name = "engine_type",nullable = false)
    @HtmlFormField(label = "Engine Type", isRequired = true)
    @TableColumnHeader(header = "Engine Type")
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column(name = "model",nullable = false)
    @HtmlFormField(label = "Vehicle Model", isRequired = true)
    @TableColumnHeader(header = "Model")
    private String model;

    @Column(name = "year",nullable = false)
    @HtmlFormField(label = "Year Manufactured", isRequired = true)
    @TableColumnHeader(header = "Year")
    @Convert(converter = YearConverter.class)
    @JsonSerialize(using = YearSerializer.class)
    @JsonDeserialize(using = YearDeserializer.class)
    @JsonFormat(pattern = "yyyy")
    private Year year;

    @Column(name = "manufacturer",nullable = false)
    @HtmlFormField(label = "Manufacturer", isRequired = true)
    //@TableColumnHeader(header = "Manufacturer")
    private String manufacturer;

    @Column(name = "color",nullable = false)
    @HtmlFormField(label = "Color", isRequired = true)
    //@TableColumnHeader(header = "Color")
    private String color;

    @Column(name = "transmission",nullable = false)
    @HtmlFormField(label = "Transmission", isRequired = true)
    @TableColumnHeader(header = "Transmission")
    @Enumerated(EnumType.STRING)
    private Transmission transmission;


    @Column(name = "number_plate",nullable = false)
    @HtmlFormField(label = "Number Plate", isRequired = true)
    @TableColumnHeader(header = "Number Plate")
    private String numberPlate;


    @Column(name = "tyre_number",nullable = false)
    @HtmlFormField(label = "Tyre Number", isRequired = true)
    @TableColumnHeader(header = "Tyres No.")
    @Positive
    private Integer tyreNumber;

    @OneToMany(mappedBy = "vehicle")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Maintenance> maintenances = new ArrayList<>();
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Assignee vehicleAssignee;

    public Vehicle() {
    }


}

