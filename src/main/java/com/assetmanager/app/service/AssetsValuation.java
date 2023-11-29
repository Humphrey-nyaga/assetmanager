package com.assetmanager.app.service;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.view.html.SummaryHtmlCard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssetsValuation implements Serializable,AssetsValuationI {
    public AssetsValuation() {
    }

    String currency = "$";

    @SummaryHtmlCard(name = "Assets Value By Category")
    public Map<String, String> totalAssetValueByCategory(List<Asset> assetList) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

        return assetList.stream()
                .collect(Collectors.groupingBy(
                        asset -> asset.getCategory().getName(),
                        Collectors.reducing(BigDecimal.ZERO, Asset::getPurchaseValue, BigDecimal::add)
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> currency + " " + decimalFormat.format(entry.getValue())
                ));
    }


    @SummaryHtmlCard(name = "Total Assets Value")
    public BigDecimal totalAssetsValue(List<Asset> assetList) {
        return assetList.stream().map(Asset::getPurchaseValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @SummaryHtmlCard(name = "Asset Count By Category")
    public Map<String, Long> countAssetsByCategory(List<Asset> assetList) {
        return assetList.stream()
                .collect(Collectors.groupingBy(
                        asset -> asset.getCategory().getName(),
                        Collectors.counting()
                ));
    }

    @SummaryHtmlCard(name = "Total Assets Count")
    public Integer totalAssets(List<Asset> assetList) {
        return assetList.size();
    }
}
