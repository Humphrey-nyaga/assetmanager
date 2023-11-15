package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.AssetRequest;

public interface AssetRequestBeanI {
    Boolean createAssetRequest(AssetRequest assetRequest);
    void deleteAssetRequest(AssetRequest assetRequest);

}
