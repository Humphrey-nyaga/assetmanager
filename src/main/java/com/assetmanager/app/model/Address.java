package com.assetmanager.app.model;

import com.assetmanager.app.view.html.HtmlFormField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class Address implements Serializable {
    @HtmlFormField(label = "postalAddress")
    @Column(name = "postal_address")
    private String postalAddress;

    @HtmlFormField(label = "postalCode")
    @Column(name = "postal_code")
    private String postalCode;

    @Column
    @HtmlFormField(label = "street")
    private String street;

    @Column
    @HtmlFormField(label = "town")
    private String town;

    @Column
    @HtmlFormField(label = "country")
    private String country;
}
