package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Category;
import com.assetmanager.app.model.entity.RequestStatusEnum;
import com.assetmanager.database.Database;
import com.assetmanager.database.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssetRequestBean extends GenericBean<AssetRequest> implements AssetRequestBeanI {

    Database database = Database.getDatabaseInstance();
    AssigneeBeanI assigneeBean = new AssigneeBean();

    @Override
    public AssetRequest create(AssetRequest assetRequest) {
        try {
            String sql = "INSERT INTO asset_request" +
                    " (asset_request_id, staff_id, asset_name, description, date_requested, quantity, request_status)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            Connection conn = MysqlDatabase.getDatabaseInstance().getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, assetRequest.getId());
            preparedStatement.setString(2, assetRequest.getStaffId());
            preparedStatement.setString(3, assetRequest.getAssetName());
            preparedStatement.setString(4, assetRequest.getDescription());
            preparedStatement.setDate(5, Date.valueOf(assetRequest.getDateRequested()));
            preparedStatement.setInt(6,assetRequest.getQuantity());
            preparedStatement.setString(7, String.valueOf(assetRequest.getRequestStatus()));
            preparedStatement.execute();
            return assetRequest;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(AssetRequest assetRequest) {

    }

    @Override
    public List<AssetRequest> list() {
        List<AssetRequest> requests = new ArrayList<>();
        try {
            Connection connection = MysqlDatabase.getDatabaseInstance().getConnection();
            String findAllAssets = "SELECT * FROM asset_request;";
            PreparedStatement preparedStatement = connection.prepareStatement(findAllAssets);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                AssetRequest assetRequest = new AssetRequest();
                assetRequest.setId(resultSet.getString("asset_request_id"));
                assetRequest.setStaffId(resultSet.getString("staff_id"));
                assetRequest.setAssetName(resultSet.getString("asset_name"));
                assetRequest.setDescription(resultSet.getString("description"));
                assetRequest.setDateRequested(resultSet.getDate("date_requested").toLocalDate());
                assetRequest.setQuantity(resultSet.getInt("quantity"));
                assetRequest.setRequestStatus(RequestStatusEnum.valueOf(resultSet.getString("request_status")));
                requests.add(assetRequest);
            }

            return requests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
