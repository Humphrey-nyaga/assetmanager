package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;
import com.assetmanager.util.idgenerator.IdPrefix;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Table(name = "asset_request")
@IdPrefix(prefix = "ASR-")
@Getter @Setter
@HtmlForm(url = "./request", label = "Asset Request")
@HtmlTable(name = "Asset Requests", label = "Asset Request", addUrl = "./request?action=add",url = "./request",updateUrl = "./updateRequest")
public class AssetRequest extends BaseEntity implements Serializable {

    @Column(name = "staff_id",nullable = false)
    @TableColumnHeader(header = "Staff ID")
    @HtmlFormField(label = "Staff ID",isRequired = true)
    private String staffId;

    @Column(name = "asset_request_id",nullable = false)
    @TableColumnHeader(header = "Request ID")
    private String assetRequestID;

    @Column(name = "asset_name",nullable = false)
    @TableColumnHeader(header = "Asset")
    @HtmlFormField(label = "Asset Name",isRequired = true)
    private String assetName;

    @Column(name = "description",nullable = false, columnDefinition = "longtext")
    @TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description", isTextArea = true)
    private String description;

    @Column(name = "date_requested",nullable = false)
    @TableColumnHeader(header = "Request Date")
    @HtmlFormField(label = "Request Date",isRequired = true)
    private LocalDate dateRequested;

    @Column(name = "quantity",nullable = false)
    @TableColumnHeader(header = "Quantity")
    @HtmlFormField(label = "Quantity",isRequired = true)
    private Integer quantity;

    @Column(name = "request_status",nullable = false)
    @TableColumnHeader(header = "Status")
    @HtmlFormField(label = "Request Status")
    private RequestStatus requestStatus;

    @Column(name="asset_category")
    @Enumerated(EnumType.STRING)
    @HtmlFormField(label = "Asset Type", isRequired = true)
    private Category category;

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
