package com.assetmanager.app.model.entity.computer;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.view.html.AssetCreationCard;
import com.assetmanager.app.view.html.HtmlFormField;

import javax.persistence.*;
import javax.validation.constraints.Positive;


@Entity
@Table(name = "computer",
        indexes = {
                @Index(name = "idx_computer_type", columnList = "computer_type"),
                @Index(name = "idx_manufacturer_model", columnList = "manufacturer, model")
        }
)
@AssetCreationCard(label = "Computer & Related ",addUrl = "./computer?action=add")
public class Computer extends Asset {

    @Column(name = "ram_in_gb")
    @HtmlFormField(label = "RAM(GB)", isRequired = true)
    @Positive
    private Double ramInGB;

    @Column(name = "screen_size")
    @HtmlFormField(label = "Screen Size", isRequired = true)
    private String screenSize;

    @Column(name = "manufacturer")
    @HtmlFormField(label = "Manufacturer", isRequired = true)
    private String manufacturer;

    @Column(name = "model")
    @HtmlFormField(label = "Model", isRequired = true)
    private String model;

    @Column(name = "storage_size_in_gb")
    @HtmlFormField(label = "Storage(GB)", isRequired = true)
    @Positive
    private Double storageSizeInGB;

    @Column(name = "condition")
    @HtmlFormField(label = "Condition", isRequired = true)
    private String condition;

    @Column(name = "processor")
    @HtmlFormField(label = "Processor", isRequired = true)
    private String processor;

    @Enumerated(EnumType.STRING)
    @Column(name = "computer_type")
    @HtmlFormField(label = "Type", isRequired = true)
    private ComputerType computerType;
    public Computer(){}

    public Double getRamInGB() {
        return ramInGB;
    }

    public void setRamInGB(Double ramInGB) {
        this.ramInGB = ramInGB;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getStorageSizeInGB() {
        return storageSizeInGB;
    }

    public void setStorageSizeInGB(Double storageSizeInGB) {
        this.storageSizeInGB = storageSizeInGB;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerType computerType) {
        this.computerType = computerType;
    }
}
