package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;

import java.io.Serializable;
import java.time.LocalDate;

@HtmlForm(label = "Assignee", url = "./assignee")
@HtmlTable(name = "Assignee Table", label = "Assignee",addUrl = "./assignee?action=add")
public class Assignee implements Serializable {
    @TableColumnHeader(header = "ID")
    private Long id;
    @TableColumnHeader(header = "Staff Number")
    @HtmlFormField(label = "Staff Number")
    private String staffNumber;
    @TableColumnHeader(header = "First Name")
    @HtmlFormField(label = "First Name")
    private String firstName;
    @TableColumnHeader(header = "Last Name")
    @HtmlFormField(label = "Last Name")
    private String lastName;
    @TableColumnHeader(header = "Email")
    @HtmlFormField(label = "Email")
    private String email;
    @TableColumnHeader(header = "Date of Birth")
    @HtmlFormField(label = "Date of Birth")
    private LocalDate dateOfBirth;
    @TableColumnHeader(header = "National ID")
    @HtmlFormField(label = "ID Number")
    private String identificationNumber;

    public Assignee() {
    }

    public Assignee(Long id, String staffNumber, String firstName, String lastName, String email, LocalDate dateOfBirth, String identificationNumber) {
        this.id = id;
        this.staffNumber = staffNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.identificationNumber = identificationNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
}
