package com.assetmanager.app.model.entity;

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
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "asset_request")
@IdPrefix(prefix = "ASR-")
@Getter @Setter
@HtmlForm(url = "./request", label = "Asset Request")
@HtmlTable(name = "Asset Requests", label = "Asset Request", addUrl = "./request?action=add",url = "./request",updateUrl = "./request?action=update")
public class AssetRequest extends BaseEntity {

    @Column(name = "staff_id",nullable = false)
    //@TableColumnHeader(header = "Assignee")
    @HtmlFormField(label = "Assignee",isRequired = true, selectList = "assignees", selectValue = "staffNumber", selectValueInSuper=false, selectDisplay = "fullName")
    private String staffId;

    @TableColumnHeader(header = "Requester Name", sortable = true)
    @Formula("(select concat(a.firstname, ' ', a.lastname) from assignee a where a.staff_id = staff_id)")
    private String fullName;

    @Column(name = "asset_request_serial_no",nullable = false)
    @TableColumnHeader(header = "Request Serial No" ,sortable = true)
    private String assetRequestSerialNumber;


    @Column(name = "asset_name",nullable = false)
    @TableColumnHeader(header = "Asset")
    @HtmlFormField(label = "Asset Name",isRequired = true)
    private String assetName;


    @Column(name = "description",nullable = false, columnDefinition = "longtext")
    @TableColumnHeader(header = "Description")
    @HtmlFormField(label = "Description", isTextArea = true)
    private String description;


    @Column(name = "date_requested",nullable = false)
    @TableColumnHeader(header = "Request Date" ,sortable = true)
    @HtmlFormField(label = "Request Date",isRequired = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRequested;


    @Column(name = "quantity",nullable = false)
    @TableColumnHeader(header = "Quantity")
    @HtmlFormField(label = "Quantity",isRequired = true)
    private Integer quantity;


    @Column(name = "request_status",nullable = false)
    @TableColumnHeader(header = "Status" ,sortable = true)
    @HtmlFormField(label = "Request Status")
    private RequestStatus requestStatus;


    @Column(name="asset_category")
    @Enumerated(EnumType.STRING)
    @HtmlFormField(label = "Asset Type", isRequired = true)
    private Category category;


    @ManyToOne
    private Assignee assignee;

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
