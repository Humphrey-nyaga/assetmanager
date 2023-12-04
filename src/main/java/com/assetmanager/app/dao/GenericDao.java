package com.assetmanager.app.dao;

import lombok.Getter;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GenericDao<T> implements GenericDaoI<T> {
    private EntityManager em;

    @SuppressWarnings({"unchecked"})
    @Override
    public List<T> list(Object entity) {
        String jpql = "FROM " + entity.getClass().getSimpleName() + " e";
        return (List<T>) em.createQuery(jpql, entity.getClass()).getResultList();

    }


    @Override
    public void addOrUpdate(T entity) {
        em.merge(entity);

    }

    @Override
    public void delete(T entity) {

    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
