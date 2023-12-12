package com.assetmanager.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AssignAssetDTO implements Serializable {
    private String assetSerialId;
    private String assigneeId;

    private AssetAssignAction assignaction;

    @Override
    public String toString() {
        return "AssignAssetDTO{" +
                "assetSerialNumber='" + assetSerialId + '\'' +
                ", assigneeId='" + assigneeId + '\'' +
                ", assetAssignAction=" + assignaction +
                '}';
    }
}
