package com.assetmanager.app.dto;

import java.io.Serializable;

public class AssigneeDTO implements Serializable {
    private Long id;
    private String fullName;

    public AssigneeDTO(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

