package com.assetmanager.app.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.TableColumnHeader;
import org.apache.commons.lang3.StringUtils;
@HtmlForm(label = "Asset", url = "./asset")
public class Asset implements Serializable {
    @TableColumnHeader(header = "Asset ID")
    @HtmlFormField(label = "Asset ID")
    private String assetId;
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

    public Asset(String assetId, String name, String description, LocalDate dateAcquired, Category category,
                 BigDecimal purchaseValue) {
        this.assetId = assetId;
        this.name = name;
        this.description = description;
        this.dateAcquired = dateAcquired;
        this.category = category;
        this.purchaseValue = purchaseValue;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
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
