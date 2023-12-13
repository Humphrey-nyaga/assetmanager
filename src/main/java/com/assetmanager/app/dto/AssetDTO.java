package com.assetmanager.app.dto;

import com.assetmanager.app.model.entity.Assignee;

import java.io.Serializable;

public class AssetDTO implements Serializable {
    private String serialNumber;
    private String name;

    private Long assigneeId;

    public AssetDTO(String serialNumber, String name, Long assigneeId) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.assigneeId = assigneeId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }
}
