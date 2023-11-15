package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Maintenance;

import java.util.List;

public interface MaintenanceBeanI {
    Boolean create(Maintenance maintenance);
    void delete(Maintenance maintenance);
    List<Maintenance> list();

}
