package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.AssetRequest;

import java.util.List;
import java.util.Map;

public interface AssetRequestBeanI extends GenericBeanI<AssetRequest> {
    AssetRequest getRequest(Long id);

    List<AssetRequest> getAssigneeAssetRequests(Long assigneeId);

    Map<String, Long> countAssigneeRequestsByCategory(Long assigneeId);
}
