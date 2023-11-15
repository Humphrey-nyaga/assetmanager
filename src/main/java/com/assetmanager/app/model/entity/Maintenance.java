package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;

import java.io.Serializable;
import java.time.LocalDate;
@HtmlForm(url = "./maintenance", label = "Maintenance")
@HtmlTable(name = "Maintenance",label = "Maintenance")
public class Maintenance implements Serializable {
    @TableColumnHeader(header = "Maintenance ID")
    private int maintenanceId;
    @TableColumnHeader(header = "Asset ID")
    private int assetId;
    @TableColumnHeader(header = "Maintenance Type")
    private String maintenanceType;
    @TableColumnHeader(header = "Scheduled Date")
    private LocalDate scheduledMaintenanceDate;
    @TableColumnHeader(header = "Actual Date")
    private LocalDate actualMaintenanceDate;
    @TableColumnHeader(header = "Description")
    private String description;
    @TableColumnHeader(header = "Cost")
    private double cost;
    @TableColumnHeader(header = "Status")
    private String status;


    public Maintenance() {
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
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
