package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;

import javax.ejb.Remote;
import java.util.List;
import java.util.Map;

@Remote
public interface AssetBeanI extends GenericBeanI<Asset> {
    Asset findAssetBySerialNumber(String id, Object entity);

    List<Asset> findAssetsByAssigneeID(Long assigneeID);

    Map<String, String> assetsValueByCategory();

    void deleteBySerialNumber(Object entity, String serialNumber);
}
