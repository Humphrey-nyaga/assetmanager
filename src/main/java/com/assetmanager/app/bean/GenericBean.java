package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.*;
import com.assetmanager.database.Database;
import com.assetmanager.database.MysqlDatabase;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenericBean<T> implements GenericBeanI<T> {
    Database database = Database.getDatabaseInstance();

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> list() {
        Class clazz = ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);


        if (clazz.equals(Asset.class))
            return (List<T>) database.getAssetList();

        if (clazz.equals(AssetRequest.class))
            return (List<T>) database.getAssetRequestsList();

        if (clazz.equals(Assignee.class))
            return (List<T>) database.getAssigneeList();

        if (clazz.equals(User.class))
            return (List<T>) database.getUsersList();

        return new ArrayList<>();
    }

    @Override
    public T create(T entity) {
        Database database = Database.getDatabaseInstance();

        Class clazz = entity.getClass();
        System.out.println(clazz.getName());

        if (entity instanceof Asset)
            database.getAssetList().add((Asset) entity);

        else if (entity instanceof AssetRequest)
            database.getAssetRequestsList().add((AssetRequest) entity);

        else if (entity instanceof Maintenance)
            database.getMaintenanceList().add((Maintenance) entity);

        else if (entity instanceof Assignee)
            ((Assignee) entity).setId(100L);
        if (entity instanceof Assignee) {
            database.getAssigneeList().add((Assignee) entity);
        }
        return entity;

    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void insert(T entity) {
        Class<?> clazz = entity.getClass();

        DbTable dbTable = clazz.getAnnotation(DbTable.class);

        if (dbTable == null) {
            throw new IllegalArgumentException("DbTable annotation is absent for the entity class");
        }

        String tableName = dbTable.name();

        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        String prefix = "";

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            DbColumn column = field.getAnnotation(DbColumn.class);
            if (column != null) {
                String columnName = column.name().isEmpty() ? field.getName() : column.name();
                columns.append(prefix).append(columnName);
                values.append(prefix).append("?");

                prefix = ", ";
            }
        }

        StringBuilder insertQuery = new StringBuilder("INSERT INTO ")
                .append(tableName)
                .append(" (")
                .append(columns)
                .append(") VALUES (")
                .append(values)
                .append(");");

        try {
            Connection connection = MysqlDatabase.getDatabaseInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery.toString());
            int parameterIndex = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                DbColumn dbColumn = field.getAnnotation(DbColumn.class);
                if (dbColumn != null) {
                    Object value = field.get(entity);

                    if (field.getType().isEnum()) {
                        value = ((Enum<?>) value).name();
                    } else if (value instanceof LocalDate) {
                        value = Date.valueOf((LocalDate) value);
                    }

                    preparedStatement.setObject(parameterIndex++, value);
                }
            }
            preparedStatement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException("Error inserting data into the database", e);
        }
    }

}

