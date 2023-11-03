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

        StringBuilder trBuilder = new StringBuilder();

        trBuilder.append("<tr>");
        trBuilder.append("<td>").append(StringUtils.trimToEmpty(getAssetId())).append("</td>");
        trBuilder.append("<td>").append(StringUtils.trimToEmpty(getName())).append("</td>");
        trBuilder.append("<td>").append(StringUtils.trimToEmpty(getDescription())).append("</td>");
        trBuilder.append("<td>").append(getCategory()).append("</td>");
        trBuilder.append("<td>").append(getDateAcquired()).append("</td>");
        trBuilder.append("<td>").append(getPurchaseValue() == null ? ""
                : new DecimalFormat("#,###.##").format(getPurchaseValue())).append("</td>");
        trBuilder.append("<tr>");

        return trBuilder.toString();
    }
}
