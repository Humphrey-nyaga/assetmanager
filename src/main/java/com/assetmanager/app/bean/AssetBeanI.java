package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;

import javax.ejb.Remote;
import java.util.List;
import java.util.Optional;
@Remote
public interface AssetBeanI extends GenericBeanI<Asset> {
    List<Asset> findAssetsByAssigneeID(String staffID);
}
