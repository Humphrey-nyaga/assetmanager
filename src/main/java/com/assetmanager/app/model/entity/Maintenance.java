package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.database.helper.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
@Getter @Setter
@DbTable(name = "maintenance")
@HtmlForm(url = "./maintenance", label = "Maintenance")
@HtmlTable(name = "Maintenance", label = "Maintenance", addUrl = "./maintenance?action=add",url = "./maintenance")
public class Maintenance extends BaseEntity implements Serializable {

    @DbColumn(name = "asset_serial_id")
    @NotNull
    @TableColumnHeader(header = "Asset Serial ID")
    @HtmlFormField(label = "Asset Serial ID")
    private String assetSerialID;

    @DbColumn(name = "maintenance_type")
    @NotNull
    @TableColumnHeader(header = "Maintenance Type")
    @HtmlFormField(label = "Maintenance Type")
    private MaintenanceType maintenanceType;

    @DbColumn(name = "scheduled_maintenance_date", definition = "DATE")
    @TableColumnHeader(header = "Scheduled Date")
    @HtmlFormField(label = "Scheduled maintenance date")
    private LocalDate scheduledMaintenanceDate;

    @DbColumn(name = "actual_maintenance_date", definition = "DATE")
    @TableColumnHeader(header = "Actual Date")
    private LocalDate actualMaintenanceDate;

    @DbColumn(name = "description")
    @TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description")
    private String description;

    @DbColumn(name = "cost", definition = "Decimal(10,2)")
    @TableColumnHeader(header = "Cost")
    @HtmlFormField(label = "Cost")
    private BigDecimal cost;

    @DbColumn(name = "status")
    @TableColumnHeader(header = "Status")
    @HtmlFormField(label = "Status")
    private String status;

    @DbColumn(name = "maintenance_period")
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