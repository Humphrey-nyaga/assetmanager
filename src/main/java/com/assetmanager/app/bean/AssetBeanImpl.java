package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Category;
import com.assetmanager.database.Database;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AssetBeanImpl implements Serializable, AssetBeanI {
    @Override
    public Asset addAsset(Asset asset) {
        return asset;
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
        Database database = Database.getDatabaseInstance();

        StringBuilder trBuilder = new StringBuilder();

        for (Asset asset : database.getAssetList())
            trBuilder.append(asset.tableRow());

        return trBuilder.toString();
    }
}
