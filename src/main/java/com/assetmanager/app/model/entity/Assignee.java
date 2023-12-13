package com.assetmanager.app.model.entity;

import com.assetmanager.app.model.entity.Machinery.Machinery;
import com.assetmanager.app.model.entity.vehicle.Vehicle;
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
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assignee")
@HtmlForm(label = "Assignee", url = "./assignee")
@HtmlTable(name = "Assignee Table", label = "Assignee", addUrl = "./assignee?action=add", updateUrl = "./updateAssignee", url = "./assignee")
@NamedQuery(
        name = Assignee.AssigneeNameAndId,
        query = "SELECT a.id, concat(a.firstName, ' ', a.lastName) FROM Assignee a"
)
public class Assignee extends BaseEntity {

    public static final String AssigneeNameAndId = "Assignee.AssigneeNameAndId";

    @Column(name = "staff_id", nullable = false, unique = true)
    @TableColumnHeader(header = "Staff Number" ,sortable = true)
    //   @HtmlFormField(label = "Staff Number")
    private String staffNumber;

    @Column(name = "employee_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @TableColumnHeader(header = "Employee Type", sortable = true)
    @HtmlFormField(label = "Employee Type")
    private AssigneeType employeeType;

    @Column(name = "firstname", nullable = false)
    @TableColumnHeader(header = "First Name", sortable = true)
    @HtmlFormField(label = "First Name")
    private String firstName;

    @Column(name = "lastname", nullable = false)
    @TableColumnHeader(header = "Last Name" ,sortable = true)
    @HtmlFormField(label = "Last Name")
    private String lastName;


    //@TableColumnHeader(header = "Requester Name")
    @Formula("(select concat(a.firstname, ' ', a.lastname) from assignee a where a.staff_id = staff_id)")
    private String fullName;

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
    @OneToMany(mappedBy = "assignee")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AssetRequest> assetRequests = new ArrayList<>();

//    @OneToMany(mappedBy = "assignee",fetch = FetchType.EAGER)
//    private List<Vehicle> assignedVehicles = new ArrayList<>();
//
//    @OneToMany(mappedBy = "assignee",fetch = FetchType.EAGER)
//    @Fetch(value = FetchMode.SELECT)
//    private List<Machinery> assignedMachinery = new ArrayList<>();

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

    public String getFullName() {
        return fullName;
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
