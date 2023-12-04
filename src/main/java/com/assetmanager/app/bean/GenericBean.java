package com.assetmanager.app.bean;

import com.assetmanager.app.dao.GenericDao;
import com.assetmanager.app.dao.GenericDaoI;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

public abstract class GenericBean<T> implements GenericBeanI<T> {

    @Inject
    private GenericDaoI<T> genericDao;
    @PersistenceContext
    EntityManager em;

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> list(Object entity) {
        genericDao.setEm(em);
        return genericDao.list(entity);
    }

    @Override
    public void addOrUpdate(T entity) {
        genericDao.setEm(em);
        genericDao.addOrUpdate(entity);
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public void delete(T entity) {

    }

    public GenericDao<T> getDao() {
        genericDao.setEm(em);
        return (GenericDao<T>) genericDao;
    }




}

