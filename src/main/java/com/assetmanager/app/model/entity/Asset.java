package com.assetmanager.app.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;
import com.assetmanager.util.idgenerator.IdPrefix;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Table(name = "assets")
@MappedSuperclass
@HtmlForm(label = "Asset", url = "./asset")
@IdPrefix(prefix = "ASN00")
@HtmlTable(name = "Asset Table", label = "Asset", url = "./asset")
public  class Asset extends BaseEntity implements Serializable {

    @Column(name = "serial_id",unique = true)
    @TableColumnHeader(header = "Serial Number")
    // @HtmlFormField(label = "Serial Number", isRequired = true)
    private String serialNumber;
    @Column(name = "name")
    @TableColumnHeader(header = "Name of Asset")
    @HtmlFormField(label = "Name", isRequired = true)
    private String name;

    @Column(name = "description")
    @TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description",isTextArea = true)
    private String description;

    @Column(name = "date_Acquired")
    @TableColumnHeader(header = "Date Acquired")
    @HtmlFormField(label = "Date Acquired", isRequired = true)
    private LocalDate dateAcquired;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    @TableColumnHeader(header = "Category")
    @HtmlFormField(label = "Category")
    private Category category;

    @Column(name = "purchase_value")
    @TableColumnHeader(header = "Value")
    @HtmlFormField(label = "Purchase Value($)", isRequired = true)
    @Positive
    private BigDecimal purchaseValue;

    @Transient
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
