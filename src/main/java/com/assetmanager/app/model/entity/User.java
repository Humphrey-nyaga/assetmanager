package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.HtmlTable;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.database.helper.NotNull;

import java.io.Serializable;
import java.util.Objects;

@HtmlTable(name = "Users")
@DbTable(name="users")
public class User extends BaseEntity implements Serializable {
    @DbColumn(name = "username")
    @NotNull
    private String username;
    @DbColumn(name = "password")
    @NotNull
    private String password;
    private String confirmPassword;
    private UserRole userRole;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public User() {
    }

    public User(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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


}
