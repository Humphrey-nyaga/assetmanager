package com.assetmanager.app.dao;

import com.assetmanager.database.MysqlDatabase;

import java.util.List;

public class GenericDao<T> implements GenericDaoI<T>  {
    private MysqlDatabase database;
    @Override
    public List<T> list(Object entity) {
        return (List<T>) database.select(entity);
    }

    @Override
    public void create(T entity) {
        database.insert(entity);

    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public void delete(T entity) {

    }

    public MysqlDatabase getDatabase() {
        return database;
    }

    @Override
    public void deleteById(Class<?> clazz,Long id) {
        database.deleteById(clazz,id);
    }

    public void setDatabase(MysqlDatabase database) {
        this.database = database;
    }
}
