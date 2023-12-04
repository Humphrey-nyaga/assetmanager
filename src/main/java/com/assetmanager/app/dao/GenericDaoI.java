package com.assetmanager.app.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDaoI<T> extends Serializable {

    List<T> list(Object entity);

    void addOrUpdate(T entity);

    void delete(T entity);

    default Optional<T> findById(String id) {
        return Optional.empty();
    }
    public EntityManager getEm();

    public void setEm(EntityManager em);
}