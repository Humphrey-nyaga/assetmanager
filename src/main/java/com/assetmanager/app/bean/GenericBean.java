package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.*;
import com.assetmanager.database.Database;
import com.assetmanager.database.MysqlDatabase;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.database.helper.PrimaryKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericBean<T> implements GenericBeanI<T> {
    Database database = Database.getDatabaseInstance();

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> list(Class<?> clazz) {

        try {
            DbTable dbTable = clazz.getAnnotation(DbTable.class);
            if (dbTable == null)
                throw new IllegalArgumentException("DbTable annotation is absent for the entity class");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM ")
                    .append(dbTable.name()).append(";");
            Connection conn = MysqlDatabase.getDatabaseInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(stringBuilder.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                T object  = (T) clazz.getDeclaredConstructor().newInstance();
                for (Field field: clazz.getDeclaredFields()){


                }
            }


            return new ArrayList<>();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        public void create (T entity){
            MysqlDatabase.insert(entity);

        }

        @Override
        public T update (T entity){
            return null;
        }

        @Override
        public void delete (T entity){

        }


    }

