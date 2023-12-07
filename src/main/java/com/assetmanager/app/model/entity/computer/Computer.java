package com.assetmanager.app.model.entity.computer;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.BaseEntity;
import com.assetmanager.app.model.entity.Machinery.Condition;
import com.assetmanager.app.view.html.AssetCreationCard;
import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;


@Getter @Setter
@Entity
@Table(name = "computer")
@HtmlForm(label = "Computer", url = "./computer")
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

    @Column(name = "current_condition")
    @HtmlFormField(label = "Condition", isRequired = true)
    @Enumerated(value = EnumType.STRING)
    private Condition currentCondition;

    @Column(name = "processor")
    @HtmlFormField(label = "Processor", isRequired = true)
    private String processor;

    @Enumerated(EnumType.STRING)
    @Column(name = "computer_type")
    @HtmlFormField(label = "Type", isRequired = true)
    private ComputerType computerType;

    public Computer(){}

}
