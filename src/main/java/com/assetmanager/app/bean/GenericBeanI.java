package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericBeanI<T> extends Serializable {

    List<T> list();

    T create(T entity);
    T update(T entity);

    void delete(T entity);

    default Optional<T> findById(String id) {
        return Optional.empty();
    }
    void insert(T entity);


}