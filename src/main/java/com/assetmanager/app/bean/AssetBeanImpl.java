package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.view.html.HtmlComponent;
import com.assetmanager.database.Database;
import com.assetmanager.util.logger.FileLogger;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;


public class AssetBeanImpl implements Serializable, AssetBeanI {
    private static final Logger LOGGER = FileLogger.getLogger();
    HtmlComponent<Asset> assetHtmlComponent = new HtmlComponent<>();


    Database database = Database.getDatabaseInstance();
    @Override
    public Asset createAsset(Asset asset) {
        database.getAssetList().add(asset);
        LOGGER.info("******Asset created******");

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
        List<Asset> assets = database.getAssetList();
        LOGGER.info("Retrieving All assets");
        return assetHtmlComponent.table(assets);
    }
}
