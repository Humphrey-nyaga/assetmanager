package com.assetmanager.app.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.database.helper.NotNull;
import com.assetmanager.util.idgenerator.IdPrefix;

@DbTable(name = "assets")
@HtmlForm(label = "Asset", url = "./asset")
@IdPrefix(prefix = "ASN00")
@HtmlTable(name = "Asset Table", label = "Asset", addUrl = "./asset?action=add", url = "./asset")
public class Asset extends BaseEntity implements Serializable {

    @DbColumn(name = "serial_id")
    @NotNull
    @TableColumnHeader(header = "Serial Number")
    //@HtmlFormField(label = "Serial Number", isRequired = true)
    private String serialNumber;
    @DbColumn(name = "name")
    @NotNull
    @TableColumnHeader(header = "Name of Asset")
    @HtmlFormField(label = "Name", isRequired = true)
    private String name;
    @DbColumn(name = "description")
    @TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description")
    private String description;
    @DbColumn(name = "date_Acquired", definition = "DATE")
    @NotNull
    @TableColumnHeader(header = "Date Acquired")
    @HtmlFormField(label = "Date Acquired", isRequired = true)
    private LocalDate dateAcquired;
    @DbColumn(name = "category")
    @TableColumnHeader(header = "Category")
    @HtmlFormField(label = "Category")
    private Category category;
    @DbColumn(name = "purchase_value", definition = "DECIMAL(10,2)")
    @TableColumnHeader(header = "Value")
    @HtmlFormField(label = "Purchase Value", isRequired = true)
    private BigDecimal purchaseValue;
    //@DbColumn(name = "staff_id")
    @TableColumnHeader(header = "Assignee Staff ID")
    private String assigneeStaffID;


    public Asset() {
    }

    public Asset(String name, String description, LocalDate dateAcquired, Category category,
                 BigDecimal purchaseValue) {
        this.name = name;
        this.description = description;
        this.dateAcquired = dateAcquired;
        this.category = category;
        this.purchaseValue = purchaseValue;
    }



    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(LocalDate dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(BigDecimal purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public String getAssigneeStaffID() {
        return assigneeStaffID;
    }

    public void setAssigneeStaffID(String assigneeStaffID) {
        this.assigneeStaffID = assigneeStaffID;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateAcquired=" + dateAcquired +
                ", category=" + category +
                ", purchaseValue=" + purchaseValue +
                ", assigneeStaffID='" + assigneeStaffID + '\'' +
                '}';
    }
}

