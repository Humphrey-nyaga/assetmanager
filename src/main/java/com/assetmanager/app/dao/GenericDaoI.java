package com.assetmanager.app.dao;

import com.assetmanager.database.MysqlDatabase;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDaoI<T> extends Serializable {

    List<T> list(Class<?> clazz);

    void create(T entity);

    T update(T entity);

    void delete(T entity);

    default Optional<T> findById(String id) {
        return Optional.empty();
    }

    void setDatabase(MysqlDatabase database);

    MysqlDatabase getDatabase();
    void deleteById(Class<?> clazz, Long id);

}