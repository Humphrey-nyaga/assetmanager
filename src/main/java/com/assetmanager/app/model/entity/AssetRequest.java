package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.database.helper.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@DbTable(name = "asset_request")
@HtmlForm(url = "./request", label = "Asset Request")
@HtmlTable(name = "Asset Requests", label = "Asset Request", addUrl = "./request?action=add",url = "./request")
public class AssetRequest extends BaseEntity implements Serializable {

    @DbColumn(name = "staff_id")
    @NotNull
    @TableColumnHeader(header = "Staff ID")
    @HtmlFormField(label = "Staff ID")
    private String staffId;
    //@DbColumn(name = "asset_request_id")
    //@NotNull
    @TableColumnHeader(header = "Request ID")
    private String assetRequestID;
    @DbColumn(name = "asset_name")
    @NotNull
    @TableColumnHeader(header = "Asset")
    @HtmlFormField(label = "Asset Name")
    private String assetName;
    @DbColumn(name = "description")
    @NotNull
    @TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description")
    private String description;
    @DbColumn(name = "date_requested",definition = "DATE")
    @NotNull
    @TableColumnHeader(header = "Request Date")
    @HtmlFormField(label = "Request Date")
    private LocalDate dateRequested;
    @DbColumn(name = "quantity",definition = "INTEGER")
    @TableColumnHeader(header = "Quantity")
    @HtmlFormField(label = "Quantity")
    private int quantity;
    @DbColumn(name = "request_status")
    @NotNull
    @TableColumnHeader(header = "Status")
    @HtmlFormField(label = "Request Status")
    private RequestStatus requestStatus;

    public AssetRequest() {
    }

    public AssetRequest(String staffId, String assetName, String description, LocalDate dateRequested, int quantity, RequestStatus requestStatus) {
        super();
        this.staffId = staffId;
        this.assetName = assetName;
        this.description = description;
        this.dateRequested = dateRequested;
        this.quantity = quantity;
        this.requestStatus = requestStatus;
    }

    public String getAssetRequestID() {
        return assetRequestID;
    }

    public void setAssetRequestID(String assetRequestID) {
        this.assetRequestID = assetRequestID;
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


    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "AssetRequest{" +
                "staffId='" + staffId + '\'' +
                ", assetName='" + assetName + '\'' +
                ", description='" + description + '\'' +
                ", dateRequested=" + dateRequested +
                ", quantity=" + quantity +
                ", requestStatus=" + requestStatus +
                '}';
    }
}
