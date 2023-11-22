package com.assetmanager.app.bean;

import com.assetmanager.app.dao.GenericDao;
import com.assetmanager.app.dao.GenericDaoI;
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
    private final GenericDaoI<T> genericDao = new GenericDao<>();

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> list(Class<?> clazz) {
        return genericDao.list(clazz);
    }

    @Override
    public void create(T entity) {
        genericDao.create(entity);
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public void delete(T entity) {

    }

}

