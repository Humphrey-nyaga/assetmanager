package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.database.helper.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@DbTable(name = "maintenance")
@HtmlForm(url = "./maintenance", label = "Maintenance")
@HtmlTable(name = "Maintenance", label = "Maintenance", addUrl = "./maintenance?action=add",url = "./maintenance")
public class Maintenance extends BaseEntity implements Serializable {

    @DbColumn(name = "asset_id", definition = "INTEGER")
    @NotNull
    @TableColumnHeader(header = "Asset ID")
    @HtmlFormField(label = "Asset ID")
    private int assetId;
    @DbColumn(name = "maintenance_type")
    @NotNull
    @TableColumnHeader(header = "Maintenance Type")
    @HtmlFormField(label = "Maintenance Type")
    private String maintenanceType;
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
    @DbColumn(name = "cost", definition = "DECIMAL(10,2)")
    @TableColumnHeader(header = "Cost")
    @HtmlFormField(label = "Cost")
    private double cost;
    @DbColumn(name = "status")
    @TableColumnHeader(header = "Status")
    @HtmlFormField(label = "Status")
    private String status;


    public Maintenance() {
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public LocalDate getScheduledMaintenanceDate() {
        return scheduledMaintenanceDate;
    }

    public void setScheduledMaintenanceDate(LocalDate scheduledMaintenanceDate) {
        this.scheduledMaintenanceDate = scheduledMaintenanceDate;
    }

    public LocalDate getActualMaintenanceDate() {
        return actualMaintenanceDate;
    }

    public void setActualMaintenanceDate(LocalDate actualMaintenanceDate) {
        this.actualMaintenanceDate = actualMaintenanceDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
