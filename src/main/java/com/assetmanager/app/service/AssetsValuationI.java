package com.assetmanager.app.service;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Category;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface AssetsValuationI {
    Map<Category, String> totalAssetValueByCategory(List<Asset> assetList);

    BigDecimal totalAssetsValue(List<Asset> assetList);

    Map<Category, Long> countAssetsByCategory(List<Asset> assetList);
    Integer totalAssets(List<Asset> assetList);
}
