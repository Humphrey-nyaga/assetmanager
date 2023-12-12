package com.assetmanager.app.model.entity;

import com.assetmanager.action.AssignAssetAction;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_assignment_log")
@Getter
@Setter @NoArgsConstructor
public class AssetAssignmentLog extends BaseEntity {

    @CreationTimestamp
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss,SSS")
    @JsonIgnore
    @Column(name = "assignment_time")
    private LocalDateTime assignmentTime;

    @Column
    private String assignAssetAction;

    @Column
    private Long assigneeId;

    @Column
    private String staffNumber;

    @Column
    private String serialNumber;

    @Column
    private String category;

    public AssetAssignmentLog(String assignAssetAction, Long assigneeId, String staffNumber, String serialNumber, String category) {
        this.assignAssetAction = assignAssetAction;
        this.assigneeId = assigneeId;
        this.staffNumber = staffNumber;
        this.serialNumber = serialNumber;
        this.category = category;
    }
}
