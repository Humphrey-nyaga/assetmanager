package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.AssetRequest;

import java.util.List;

public interface AssetRequestBeanI {
    Boolean createAssetRequest(AssetRequest assetRequest);
    void deleteAssetRequest(AssetRequest assetRequest);
    List<AssetRequest> getAllAssetRequests();

}
