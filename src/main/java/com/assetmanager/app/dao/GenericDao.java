package com.assetmanager.app.dao;

import com.assetmanager.database.MysqlDatabase;

import java.util.List;

public class GenericDao<T> implements GenericDaoI<T>  {
    @Override
    public List<T> list(Class<?> clazz) {
        return (List<T>) MysqlDatabase.select(clazz);
    }

    @Override
    public void create(T entity) {
        MysqlDatabase.insert(entity);

    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public void delete(T entity) {

    }
}
