package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Category;
import com.assetmanager.database.Database;
import com.assetmanager.database.MysqlDatabase;
import com.assetmanager.util.logger.FileLogger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Stateless
public class AssetBeanImpl extends GenericBean<Asset> implements AssetBeanI {

    @EJB
    MysqlDatabase database;
    @Override
    public void delete(Asset assetToDelete) {
        Optional<Asset> optionalAsset = findAssetById(assetToDelete.getSerialNumber());

        if (optionalAsset.isPresent()) {
            try {
                Connection connection = database.getConnection();
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
            Connection connection = database.getConnection();
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


}
