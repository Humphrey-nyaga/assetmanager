package com.assetmanager.app.observer;

import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Assignee;

public record AssetRequestEvent(AssetRequest assetRequest, Assignee assignee) {

}
