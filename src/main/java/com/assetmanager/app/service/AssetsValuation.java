package com.assetmanager.app.service;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Category;
import com.assetmanager.app.view.html.SummaryHtmlCard;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssetsValuation implements Serializable {
    public AssetsValuation() {
    }

    String currency = "$";

    @SummaryHtmlCard(name = "Assets Value By Category")
    public Map<Category, String> totalAssetValueByCategory(List<Asset> assetList) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

        return assetList.stream()
                .collect(Collectors.groupingBy(
                        Asset::getCategory,
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
    public Map<Category, Long> countAssetsByCategory(List<Asset> assetList) {
        return assetList.stream()
                .collect(Collectors.groupingBy(
                        Asset::getCategory,
                        Collectors.counting()
                ));
    }

    @SummaryHtmlCard(name = "Total Assets Count")
    public Integer totalAssets(List<Asset> assetList) {
        return assetList.size();
    }
}
