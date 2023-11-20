package com.assetmanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/assetmanager";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private static MysqlDatabase database;
    private Connection connection;

    private MysqlDatabase() throws SQLException {
        connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
    }
    public static MysqlDatabase getDatabaseInstance() throws SQLException {
        if (database == null){
            database = new MysqlDatabase();
        }
        return  database;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
