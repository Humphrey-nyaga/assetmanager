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


public class AssetBeanImpl extends GenericBean<Asset> implements AssetBeanI {
    private static final Logger LOGGER = FileLogger.getLogger();


    Database database = Database.getDatabaseInstance();
    List<Asset> assets = database.getAssetList();


    @Override
    public void delete(Asset assetToDelete) {
        Optional<Asset> optionalAsset = findAssetById(assetToDelete.getId());

        if (optionalAsset.isPresent()) {
            Asset presentAsset = optionalAsset.get();
            assets.removeIf(a -> a.getId().equals(presentAsset.getId()));
        }
    }

    public Optional<Asset> findAssetById(String id) {
        return Optional.ofNullable(assets.stream()
                .filter(asset -> asset.getId().equals(id))
                .findFirst().orElseThrow(() -> new AssetNotFoundException("Asset with ID " + id + " not found")));
    }
}
