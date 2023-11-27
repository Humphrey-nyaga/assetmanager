package com.assetmanager.util.idgenerator;

import com.assetmanager.database.MysqlDatabase;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlMaxRowId {
    @Inject
    MysqlDatabase mysqlDatabase;

    public synchronized long getLastGeneratedId(String tableName) {
        String queryString = "SELECT MAX(id) FROM " + tableName;
        try {

            PreparedStatement preparedStatement = mysqlDatabase.getConnection().prepareStatement(queryString);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
