package com.assetmanager.app.dto;

import java.io.Serializable;

public class AssetDTO implements Serializable {
    private String serialNumber;
    private String name;

    public AssetDTO(String serialNumber, String name) {
        this.serialNumber = serialNumber;
        this.name = name;
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
}
