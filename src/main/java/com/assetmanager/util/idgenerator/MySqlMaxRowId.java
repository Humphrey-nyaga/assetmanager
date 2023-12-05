package com.assetmanager.util.idgenerator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class MySqlMaxRowId {

    @PersistenceContext
    private EntityManager entityManager;

    public synchronized long getLastGeneratedId(String tableName) {
        String queryString = "SELECT MAX(id) FROM :tableName";
        try {
            Query query = entityManager.createNativeQuery(queryString);
            query.setParameter("tableName", tableName);
            Object result = query.getSingleResult();
            return result instanceof Number ? ((Number) result).longValue() : 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
