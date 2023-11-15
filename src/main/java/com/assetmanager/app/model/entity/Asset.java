package com.assetmanager.app.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.assetmanager.app.bean.GenericBean;
import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;

@HtmlForm(label = "Asset", url = "./asset")
@HtmlTable(name = "Asset Table" ,label = "Asset", addUrl = "./asset?action=add")
public class Asset  implements Serializable {
    @TableColumnHeader(header = "Asset ID")
    @HtmlFormField(label = "Asset ID")
    private String id;
    @TableColumnHeader(header = "Name of Asset")
    @HtmlFormField(label = "Name")
    private String name;
    @TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description")
    private String description;
    @TableColumnHeader(header = "Date Acquired")
    @HtmlFormField(label = "Date Acquired")
    private LocalDate dateAcquired;
    @TableColumnHeader(header = "Category")
    @HtmlFormField(label = "Category")
    private Category category;
    @TableColumnHeader(header = "Value")
    @HtmlFormField(label = "Purchase Value")
    private BigDecimal purchaseValue;

    public Asset() {
    }

    public Asset(String id, String name, String description, LocalDate dateAcquired, Category category,
                 BigDecimal purchaseValue) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateAcquired = dateAcquired;
        this.category = category;
        this.purchaseValue = purchaseValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
