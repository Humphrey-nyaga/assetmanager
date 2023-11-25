package com.assetmanager.app.bean;

import com.assetmanager.app.dao.GenericDao;
import com.assetmanager.app.dao.GenericDaoI;
import com.assetmanager.app.model.entity.*;
import com.assetmanager.database.Database;
import com.assetmanager.database.MysqlDatabase;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.database.helper.PrimaryKey;

import javax.ejb.EJB;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GenericBean<T> implements GenericBeanI<T> {
    private final GenericDaoI<T> genericDao = new GenericDao<>();
    @EJB
    MysqlDatabase database;

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> list(Class<?> clazz) {
        genericDao.setDatabase(database);
        return genericDao.list(clazz);
    }

    @Override
    public void create(T entity) {
        genericDao.setDatabase(database);
        genericDao.create(entity);
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public void delete(T entity) {

    }

    public GenericDao<T> getDao() {
        genericDao.setDatabase(database);
        return (GenericDao<T>) genericDao;
    }

    @Override
    public void deleteById(Class<?> clazz, Long id) {
        genericDao.setDatabase(database);
        genericDao.deleteById(clazz, id);
    }

    ;


}

