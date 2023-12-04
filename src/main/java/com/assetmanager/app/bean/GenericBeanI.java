package com.assetmanager.app.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericBeanI<T> extends Serializable {

    List<T> list(Object entity);

    void addOrUpdate(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteById(Class<?> clazz, Long id);

    default Optional<T> findById(String id) {
        return Optional.empty();
    }


}