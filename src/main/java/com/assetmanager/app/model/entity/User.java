package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
@Getter @Setter
@Table(name = "user")
@HtmlTable(name = "Users")
public class User extends BaseEntity implements Serializable {

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "username",nullable = false, unique = true)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    public User() {
    }

    public User(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
