package com.assetmanager.app.bean;

import com.assetmanager.app.model.Asset;
import com.assetmanager.app.model.Category;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AssetBeanImpl implements Serializable, AssetBeanI {
    @Override
    public void addAsset() {

    }

    @Override
    public void updateAsset() {

    }

    @Override
    public void disposeAsset() {

    }

    @Override
    public void transferAsset() {

    }

    @Override
    public String getAllAssets() {
        List<Asset> assetList = List.of(
                new Asset("001", "Laptop", "Dell Laptop", LocalDate.of(2022, 5, 10), Category.HARDWARE,
                        new BigDecimal("999.99")),
                new Asset("002", "Software License", "Microsoft Office", LocalDate.of(2021, 8, 15), Category.SOFTWARE,
                        new BigDecimal("149.99")),
                new Asset("003", "Server", "HP ProLiant Server", LocalDate.of(2021, 12, 5), Category.HARDWARE,
                        new BigDecimal("2499.99")),
                new Asset("004", "Operating System", "Windows 10", LocalDate.of(2020, 3, 2), Category.SOFTWARE,
                        new BigDecimal("119.99")),
                new Asset("005", "Digital Artwork", "Abstract Painting", LocalDate.of(2023, 2, 18),
                        Category.DIGITAL_ASSET, new BigDecimal("299.99")));

        StringBuilder trBuilder = new StringBuilder();

        for (Asset asset : assetList)
            trBuilder.append(asset.tableRow());

        trBuilder.append("</table>");

        return trBuilder.toString();
    }
}
