package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlForm;
import com.assetmanager.app.view.html.HtmlFormField;
import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.app.view.html.TableColumnHeader;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assignee")
@HtmlForm(label = "Assignee", url = "./assignee")
@HtmlTable(name = "Assignee Table", label = "Assignee", addUrl = "./assignee?action=add", updateUrl = "./updateAssignee", url = "./assignee")
public class Assignee extends BaseEntity {

    @Column(name = "staff_id", nullable = false, unique = true)
    @TableColumnHeader(header = "Staff Number")
    //   @HtmlFormField(label = "Staff Number")
    private String staffNumber;

    @Column(name = "employee_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @TableColumnHeader(header = "Employee Type")
    @HtmlFormField(label = "Employee Type")
    private AssigneeType employeeType;

    @Column(name = "firstname", nullable = false)
    @TableColumnHeader(header = "First Name")
    @HtmlFormField(label = "First Name")
    private String firstName;

    @Column(name = "lastname", nullable = false)
    @TableColumnHeader(header = "Last Name")
    @HtmlFormField(label = "Last Name")
    private String lastName;

    @Column(name = "email", nullable = false)
    @Email
    @TableColumnHeader(header = "Email")
    @HtmlFormField(label = "Email")
    private String email;

    @Column(name = "date_of_birth", nullable = false)
    @TableColumnHeader(header = "Date of Birth")
    @HtmlFormField(label = "Date of Birth", isRequired = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "national_id", nullable = false)
    @TableColumnHeader(header = "National ID")
    @HtmlFormField(label = "ID Number")
    private String identificationNumber;
    @OneToMany(mappedBy = "assignee",fetch = FetchType.LAZY)
    private List<AssetRequest> assetRequests = new ArrayList<>();

    public Assignee() {
    }

    public Assignee(String staffNumber, String firstName, String lastName, String email, LocalDate dateOfBirth, String identificationNumber, AssigneeType employeeType) {
        this.staffNumber = staffNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.identificationNumber = identificationNumber;
        this.employeeType = employeeType;
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

    public AssigneeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(AssigneeType employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public String toString() {
        return "Assignee{" +
                "staffNumber='" + staffNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", identificationNumber='" + identificationNumber + '\'' +
                '}';
    }
}
