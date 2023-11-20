package com.assetmanager.app.usebeans;

import java.io.Serializable;


public class UserBean implements Serializable {

    private String usernameLabel = "Enter Username";
    private String passwordLabel = "Enter Password";

    public UserBean() {
    }

    public String getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(String usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public String getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(String passwordLabel) {
        this.passwordLabel = passwordLabel;
    }
}
