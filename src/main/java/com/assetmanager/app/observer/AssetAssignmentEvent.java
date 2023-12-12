package com.assetmanager.app.observer;

import com.assetmanager.app.dto.AssetAssignAction;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Assignee;

public record AssetAssignmentEvent(Asset asset, Assignee assignee, AssetAssignAction assetAssignAction) {
}
