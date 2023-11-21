package com.assetmanager.app.model.entity;

import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.NotNull;
import com.assetmanager.database.helper.PrimaryKey;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    @PrimaryKey
    @NotNull
    @DbColumn(name = "id", definition = "INTEGER")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
