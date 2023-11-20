package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.database.helper.TablePrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;

@DbTable(name = "asset_request")
@HtmlForm(url = "./request", label = "Asset Request")
@HtmlTable(name = "Asset Requests", label = "Asset Request", addUrl = "./request?action=add")
public class AssetRequest implements Serializable {
    @DbColumn(name = "asset_request_id")
    @TableColumnHeader(header = "Request ID")
    @HtmlFormField(label = "Request ID")
    private String id;
    @DbColumn(name = "staff_id")
    @TableColumnHeader(header = "Staff ID")
    @HtmlFormField(label = "Staff ID")
    private String staffId;
    @DbColumn(name = "asset_name")
    @TableColumnHeader(header = "Asset")
    @HtmlFormField(label = "Asset Name")
    private String assetName;
    @DbColumn(name = "description")
    @TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description")
    private String description;
    @DbColumn(name = "date_requested",definition = "DATE")
    @TableColumnHeader(header = "Request Date")
    @HtmlFormField(label = "Request Date")
    private LocalDate dateRequested;
    @DbColumn(name = "quantity",definition = "INTEGER")
    @TableColumnHeader(header = "Quantity")
    @HtmlFormField(label = "Quantity")
    private int quantity;
    @DbColumn(name = "request_status")
    @TableColumnHeader(header = "Status")
    @HtmlFormField(label = "Request Status")
    private RequestStatusEnum requestStatus;

    public AssetRequest() {
    }

    public AssetRequest(String id, String staffId, String assetName, String description, LocalDate dateRequested, int quantity, RequestStatusEnum requestStatus) {
        this.id = id;
        this.staffId = staffId;
        this.assetName = assetName;
        this.description = description;
        this.dateRequested = dateRequested;
        this.quantity = quantity;
        this.requestStatus = requestStatus;
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

    public int getQuantity() {
        return quantity;
    }
}
