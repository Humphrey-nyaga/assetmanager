package com.assetmanager.app.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;

public class Asset implements Serializable {
    private String assetId;
    private String name;
    private String description;
    private LocalDate dateAcquired;
    private Category category;
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

    public String tableRow() {

        String trBuilder = "<tr>" +
                "<td>" + StringUtils.trimToEmpty(getAssetId()) + "</td>" +
                "<td>" + StringUtils.trimToEmpty(getName()) + "</td>" +
                "<td>" + StringUtils.trimToEmpty(getDescription()) + "</td>" +
                "<td>" + getCategory() + "</td>" +
                "<td>" + getDateAcquired() + "</td>" +
                "<td>" + (getPurchaseValue() == null ? ""
                : new DecimalFormat("#,###.##").format(getPurchaseValue())) +
                "</td>" +
                "<tr>";

        return trBuilder;
    }
}
