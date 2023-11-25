package com.assetmanager.app.model.entity;

import com.assetmanager.app.view.html.TableColumnHeader;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.NotNull;
import com.assetmanager.database.helper.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseEntity implements Serializable {
    @PrimaryKey
    @NotNull
    @DbColumn(name = "id", definition = "INTEGER")
    @TableColumnHeader(header = "ID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @DbColumn(name = "created_at" ,definition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    @DbColumn(name = "modified_at",definition = "DATETIME ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastModifiedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
