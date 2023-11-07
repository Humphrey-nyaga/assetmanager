package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;

public interface AssetBeanI {
    Asset addAsset(Asset asset);

    void updateAsset();

    void disposeAsset();

    void transferAsset();

    String getAllAssets();

}
