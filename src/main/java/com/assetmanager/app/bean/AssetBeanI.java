package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;
import java.util.Optional;

public interface AssetBeanI extends GenericBeanI<Asset> {
    Optional<Asset> findAssetById(String id);
}
