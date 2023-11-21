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

import javax.persistence.Entity;

@DbTable(name = "assets")
@HtmlForm(label = "Asset", url = "./asset")
@HtmlTable(name = "Asset Table" ,label = "Asset", addUrl = "./asset?action=add")
public class Asset  implements Serializable {

    @DbColumn(name = "serial_id")
    @NotNull
    @TableColumnHeader(header = "Serial Number")
    @HtmlFormField(label = "Serial Number")
    private String serialNumber;
    @DbColumn(name = "name")
    @NotNull
    @TableColumnHeader(header = "Name of Asset")
    @HtmlFormField(label = "Name")
    private String name;
    @DbColumn(name = "description")
    @TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description")
    private String description;
    @DbColumn(name = "date_Acquired",definition = "DATE")
    @NotNull
    @TableColumnHeader(header = "Date Acquired")
    @HtmlFormField(label = "Date Acquired")
    private LocalDate dateAcquired;
    @DbColumn(name = "category")
    @TableColumnHeader(header = "Category")
    @HtmlFormField(label = "Category")
    private Category category;
    @DbColumn(name = "purchase_value",definition = "DECIMAL(10,2)")
    @TableColumnHeader(header = "Value")
    @HtmlFormField(label = "Purchase Value")
    private BigDecimal purchaseValue;

    public Asset() {
    }

    public Asset(String serialNumber, String name, String description, LocalDate dateAcquired, Category category,
                 BigDecimal purchaseValue) {
        this.serialNumber = serialNumber;
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

}
