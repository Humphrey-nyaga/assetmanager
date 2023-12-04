package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
@Getter @Setter
@HtmlForm(url = "./maintenance", label = "Maintenance")
@HtmlTable(name = "Maintenance", label = "Maintenance", addUrl = "./maintenance?action=add",url = "./maintenance")
public class Maintenance extends BaseEntity implements Serializable {

    @Column(name = "asset_serial_id",nullable = false)
    @TableColumnHeader(header = "Asset Serial ID")
    @HtmlFormField(label = "Asset Serial ID")
    private String assetSerialID;


    @Column(name = "maintenance_type",nullable = false)
    @Enumerated(value = EnumType.STRING)
    @TableColumnHeader(header = "Maintenance Type")
    @HtmlFormField(label = "Maintenance Type")
    private MaintenanceType maintenanceType;

    @Column(name = "scheduled_maintenance_date")
    @TableColumnHeader(header = "Scheduled Date")
    @HtmlFormField(label = "Scheduled maintenance date")
    private LocalDate scheduledMaintenanceDate;


    @Column(name = "actual_maintenance_date")
    @TableColumnHeader(header = "Actual Date")
    private LocalDate actualMaintenanceDate;


    @Column(name = "description",columnDefinition = "longtext")
    @TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description",isTextArea = true)
    private String description;


    @Column(name = "cost",nullable = false)
    @TableColumnHeader(header = "Cost")
    @HtmlFormField(label = "Cost")
    private BigDecimal cost;


    @Column(name = "status",nullable = false)
    @TableColumnHeader(header = "Status")
    @HtmlFormField(label = "Status")
    private String status;


    @Column(name = "maintenance_period")
    @Enumerated(value = EnumType.STRING)
    @HtmlFormField(label = "Maintenance Period")
    private MaintenanceFrequency maintenanceFrequency;

    public Maintenance() {
    }

    public Maintenance(String assetSerialID, MaintenanceType maintenanceType, String description, MaintenanceFrequency maintenanceFrequency) {
        this.assetSerialID = assetSerialID;
        this.maintenanceType = maintenanceType;
        this.description = description;
        this.maintenanceFrequency = maintenanceFrequency;
    }
}