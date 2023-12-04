package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;
import java.util.Optional;

@Local
public interface AssetBeanI extends GenericBeanI<Asset> {
    Optional<Asset> findAssetById(String id);

    List<Asset> findAssetsByAssigneeID(String staffID);
}
