package com.assetmanager.app.dao;

import com.assetmanager.app.model.entity.AssetRequest;
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
    public T addOrUpdate(T entity) {
        return em.merge(entity);

    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
    public void deleteById(Class<?> clazz, Long id) {
        Object o = em.find(clazz, id);
        em.remove(o);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(Class<?> clazz, Long id) {
        return (T) em.find(clazz, id);
    }


    public void setEm(EntityManager em) {
        this.em = em;
    }
}
