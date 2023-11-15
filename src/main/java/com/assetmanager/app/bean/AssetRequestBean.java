package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.database.Database;

import java.util.List;

public class AssetRequestBean implements AssetRequestBeanI {

    Database database = Database.getDatabaseInstance();
    AssigneeBeanI assigneeBean = new AssigneeBean();

    @Override
    public Boolean createAssetRequest(AssetRequest assetRequest) {

        if (assigneeBean.getAssigneeByStaffId(assetRequest.getStaffId()).isPresent()) {
            database.getAssetRequestsList().add(assetRequest);
            return true;
        } else
            return false;

    }

    @Override
    public void deleteAssetRequest(AssetRequest assetRequest) {

    }

    @Override
    public List<AssetRequest> getAllAssetRequests() {
        return database.getAssetRequestsList();
    }

}
