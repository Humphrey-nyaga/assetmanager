package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.view.html.HtmlComponent;
import com.assetmanager.database.Database;
import com.assetmanager.exceptions.AssetNotFoundException;
import com.assetmanager.util.logger.FileLogger;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


public class AssetBeanImpl implements Serializable, AssetBeanI {
    private static final Logger LOGGER = FileLogger.getLogger();


    Database database = Database.getDatabaseInstance();
    List<Asset> assets = database.getAssetList();

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

    public List<Asset> getAllAssets() {
        Database database = Database.getDatabaseInstance();
        return database.getAssetList();
    }
    @Override
    public void delete(Asset assetToDelete) {
        Optional<Asset> optionalAsset = findAssetById(assetToDelete.getId());

        if (optionalAsset.isPresent()) {
            Asset presentAsset = optionalAsset.get();
            assets.removeIf(a -> a.getId().equals(presentAsset.getId()));
        } else {
            throw new AssetNotFoundException("Asset with ID " + assetToDelete.getId() + " not found");
        }
    }

    public Optional<Asset> findAssetById(String id){
        return assets.stream()
                .filter(asset -> asset.getId().equals(id))
                .findFirst();
    }
}
