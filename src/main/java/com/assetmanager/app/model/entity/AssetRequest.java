package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;

import java.io.Serializable;
import java.time.LocalDate;
@HtmlForm(url = "/request" ,label="Asset Request")
@HtmlTable(name = "Asset Requests",addUrl = "./request?action=add")
public class AssetRequest implements Serializable {
    @TableColumnHeader(header = "Request ID")
    @HtmlFormField(label = "Request ID")
    private String id;

    @TableColumnHeader(header = "Requester ID")
    @HtmlFormField(label = "Requester ID")
    private String staffId;

    @TableColumnHeader(header = "Asset")
    @HtmlFormField(label = "Asset Name")
    private String assetName;

    @TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description")
    private String description;
    @TableColumnHeader(header = "Request Date")
    @HtmlFormField(label = "Request Date")
    private LocalDate dateRequested;
    @TableColumnHeader(header = "Quantity")
    @HtmlFormField(label = "Quantity")
    private int quantity;
    @TableColumnHeader(header = "Request status")
    private RequestStatusEnum requestStatus;
    @TableColumnHeader(header = "Category")
    private Category category;

    public AssetRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(LocalDate dateRequested) {
        this.dateRequested = dateRequested;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   
    public RequestStatusEnum getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatusEnum requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }
}
