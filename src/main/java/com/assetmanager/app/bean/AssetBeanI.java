package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Assignee;

import java.util.List;

public interface AssetBeanI {
    Asset createAsset(Asset asset);

    void updateAsset();

    void disposeAsset();

    void transferAsset();

    List<Asset> getAllAssets();
    void delete(Asset asset);

}
