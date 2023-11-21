package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Category;
import com.assetmanager.database.Database;
import com.assetmanager.database.MysqlDatabase;
import com.assetmanager.util.logger.FileLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


public class AssetBeanImpl extends GenericBean<Asset> implements AssetBeanI {
    private static final Logger LOGGER = FileLogger.getLogger();

    @Override
    public void delete(Asset assetToDelete) {
        Optional<Asset> optionalAsset = findAssetById(assetToDelete.getSerialNumber());

        if (optionalAsset.isPresent()) {
            try {
                Connection connection = MysqlDatabase.getDatabaseInstance().getConnection();
                String deleteAssetQuery = "DELETE FROM assets WHERE serial_id = ?;";

                PreparedStatement preparedStatement = connection.prepareStatement(deleteAssetQuery);
                preparedStatement.setString(1, assetToDelete.getSerialNumber());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error deleting asset with ID: " + assetToDelete.getSerialNumber(), e);
            }
        }
    }

    public Optional<Asset> findAssetById(String id) {


        try {
            Connection connection = MysqlDatabase.getDatabaseInstance().getConnection();
            String findAssetByIdQuery = "SELECT * FROM assets WHERE serial_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(findAssetByIdQuery);
            preparedStatement.setString(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Asset asset = new Asset();
                asset.setSerialNumber(rs.getString("serial_id"));
                asset.setName(rs.getString("name"));
                asset.setCategory(Category.valueOf(rs.getString("category")));
                asset.setDescription(rs.getString("description"));
                asset.setDateAcquired(rs.getDate("date_acquired").toLocalDate());
                asset.setPurchaseValue(rs.getBigDecimal("purchase_value"));
                return Optional.of(asset);
            } else {
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public List<Asset> list(Class<?> clazz) {
        List<Asset> assets = new ArrayList<>();
        try {
            Connection connection = MysqlDatabase.getDatabaseInstance().getConnection();
            String findAllAssets = "SELECT * FROM assets;";
            PreparedStatement preparedStatement = connection.prepareStatement(findAllAssets);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Asset asset = new Asset();

                asset.setSerialNumber(rs.getString("serial_id"));
                asset.setName(rs.getString("name"));
                asset.setCategory(Category.valueOf(rs.getString("category")));
                asset.setDescription(rs.getString("description"));
                asset.setDateAcquired(rs.getDate("date_acquired").toLocalDate());
                asset.setPurchaseValue(rs.getBigDecimal("purchase_value"));
                assets.add(asset);
            }


            return assets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
