package com.assetmanager.app.model.entity.computer;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Machinery.Condition;
import com.assetmanager.app.view.html.*;
import com.assetmanager.util.idgenerator.IdPrefix;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;


@Getter @Setter
@Entity
@Table(name = "computer")
@HtmlForm(label = "Computer", url = "./computer")
@AssetCreationCard(label = "Computer & Related ", servletUrl = "./computer")
@IdPrefix(prefix = "ASN-COMP000")
@HtmlTable(name = "Computer & Related Table", label = "Computer & Related", url = "./computer",updateUrl = "./computer?action=update",addUrl = "./computer?action=add")
public class Computer extends Asset {

    @Column(name = "ram_in_gb")
    @HtmlFormField(label = "RAM(GB)", isRequired = true)
    @Positive
    @TableColumnHeader(header = "RAM(GB)")
    private Double ramInGB;

    @Column(name = "screen_size")
    @HtmlFormField(label = "Screen Size (Inch)", isRequired = true)
    @TableColumnHeader(header = "Screen")
    private String screenSize;

    @Column(name = "manufacturer")
    @HtmlFormField(label = "Manufacturer", isRequired = true)
    @TableColumnHeader(header = "Manufacturer")
    private String manufacturer;

    @Column(name = "model")
    @HtmlFormField(label = "Model", isRequired = true)
    @TableColumnHeader(header = "Model")
    private String model;

    @Column(name = "storage_size_in_gb")
    @HtmlFormField(label = "Storage(GB)", isRequired = true)
    @Positive
    @TableColumnHeader(header = "Storage(GB)")
    private Double storageSizeInGB;

    @Column(name = "current_condition")
    @HtmlFormField(label = "Condition", isRequired = true)
    @Enumerated(value = EnumType.STRING)
    @TableColumnHeader(header = "Condition")
    private Condition currentCondition;

    @Column(name = "processor")
    @HtmlFormField(label = "Processor", isRequired = true)
    private String processor;

    @Enumerated(EnumType.STRING)
    @Column(name = "computer_type")
    @HtmlFormField(label = "Type", isRequired = true)
    @TableColumnHeader(header = "Type")
    private ComputerType computerType;

    public Computer(){}

    @Override
    public String toString() {
        return "Computer{" +
                super.toString()+
                "ramInGB=" + ramInGB +
                ", screenSize='" + screenSize + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", storageSizeInGB=" + storageSizeInGB +
                ", currentCondition=" + currentCondition +
                ", processor='" + processor + '\'' +
                ", computerType=" + computerType +
                '}';
    }
}
