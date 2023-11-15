package com.assetmanager.app.service;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Category;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssetsValuation implements Serializable {
    public Map<Category, BigDecimal> totalAssetValueByCategory(List<Asset> assetList) {
        return assetList.stream()
                .collect(Collectors.groupingBy(
                        Asset::getCategory,
                        Collectors.reducing(BigDecimal.ZERO, Asset::getPurchaseValue, BigDecimal::add)
                ));
    }

    public BigDecimal totalAssetsValue(List<Asset> assetList) {
        return assetList.stream().map(Asset::getPurchaseValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<Category, Long> countAssetsByCategory(List<Asset> assetList) {
        return assetList.stream()
                .collect(Collectors.groupingBy(
                        Asset::getCategory,
                        Collectors.counting()
                ));
    }
    public Integer totalAssets(List<Asset> assetList) {
        return assetList.size();
    }
}
