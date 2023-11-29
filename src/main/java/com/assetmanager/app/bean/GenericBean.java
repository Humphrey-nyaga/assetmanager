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
import javax.inject.Inject;

import java.util.List;

public abstract class GenericBean<T> implements GenericBeanI<T> {

    @Inject
    private GenericDaoI<T> genericDao;
    @EJB
    MysqlDatabase database;

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> list(Object entity) {
        genericDao.setDatabase(database);
        return genericDao.list(entity);
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


}

