package com.assetmanager.app.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDaoI<T> extends Serializable {

    List<T> list(Object entity);

    T addOrUpdate(T entity);

    void delete(T entity);

    void deleteById(Class<?> clazz, Long id);

    T findById(Class<?> clazz, Long id);

    public EntityManager getEm();

    public void setEm(EntityManager em);
}