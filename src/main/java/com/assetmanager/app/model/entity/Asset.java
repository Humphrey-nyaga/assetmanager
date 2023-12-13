package com.assetmanager.app.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;
import com.assetmanager.util.idgenerator.IdPrefix;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Table(name = "assets")
@MappedSuperclass
@HtmlForm(label = "Asset", url = "./asset")
@IdPrefix(prefix = "ASN00")
@HtmlTable(name = "Asset Table", label = "Asset", url = "./asset")
public  class Asset extends BaseEntity implements Serializable {

    @Column(name = "serial_id",unique = true)
    @TableColumnHeader(header = "Serial Number",sortable = true)
    // @HtmlFormField(label = "Serial Number", isRequired = true)
    private String serialNumber;

    @Column
    @TableColumnHeader(header = "Name of Asset",sortable = true)
    @HtmlFormField(label = "Name", isRequired = true)
    private String name;

    @Column
    //@TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description",isTextArea = true)
    private String description;

    @Column(name = "date_Acquired")
    @TableColumnHeader(header = "Date Acquired",sortable = true)
    @HtmlFormField(label = "Date Acquired", isRequired = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAcquired;

    @Column
    @Enumerated(EnumType.STRING)
    @TableColumnHeader(header = "Category",sortable = true)
   // @HtmlFormField(label = "Category")
    private Category category;

    @Column(name = "purchase_value")
    @TableColumnHeader(header = "Value",sortable = true)
    @HtmlFormField(label = "Purchase Value($)", isRequired = true)
    @Positive
    private BigDecimal purchaseValue;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id")
    private Assignee assignee;

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    @TableColumnHeader(header = "Assignee")
    @Formula("(select concat( a.firstname, ' ',a.lastname) from assignee a where a.id=assignee_id)")
    private String assigneeName;

    @Formula("(assignee_id)")
    //@TableColumnHeader(header = "Assignee ID")
    private Long assigneeId;

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

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
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
                ", assignee=" + assignee +
                ", assigneeName='" + assigneeName + '\'' +
                ", assigneeId=" + assigneeId +
                '}';
    }
}
