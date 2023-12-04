package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;
import com.assetmanager.util.idgenerator.IdPrefix;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Table(name = "asset_request")
@IdPrefix(prefix = "ASR-")
@HtmlForm(url = "./request", label = "Asset Request")
@HtmlTable(name = "Asset Requests", label = "Asset Request", addUrl = "./request?action=add",url = "./request",updateUrl = "./app/updateRequest.jsp")
public class AssetRequest extends BaseEntity implements Serializable {

    @Column(name = "staff_id",nullable = false)
    @TableColumnHeader(header = "Staff ID")
    @HtmlFormField(label = "Staff ID")
    private String staffId;

    @Column(name = "asset_request_id",nullable = false)
    @TableColumnHeader(header = "Request ID")
    private String assetRequestID;

    @Column(name = "asset_name",nullable = false)
    @TableColumnHeader(header = "Asset")
    @HtmlFormField(label = "Asset Name")
    private String assetName;

    @Column(name = "description",nullable = false,columnDefinition = "longtext")
    @TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description")
    private String description;

    @Column(name = "date_requested",nullable = false)
    @TableColumnHeader(header = "Request Date")
    @HtmlFormField(label = "Request Date")
    private LocalDate dateRequested;

    @Column(name = "quantity",nullable = false)
    @TableColumnHeader(header = "Quantity")
    @HtmlFormField(label = "Quantity")
    private int quantity;

    @Column(name = "request_status",nullable = false)
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
