package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.util.idgenerator.GenericIDGenerator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class AssetBeanImpl extends GenericBean<Asset> implements AssetBeanI {

    @Inject
    GenericIDGenerator generate;

    @PersistenceContext
    EntityManager em;

    @Override
    public void addOrUpdate(Asset entity) {
        entity.setSerialNumber(generate.generateId(entity));
        getDao().addOrUpdate(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Asset> list(Object entity) {
        String jpql = "FROM " + entity.getClass().getName() + " e";
        return em.createQuery(jpql, Asset.class)
                .getResultList();
    }


    @Override
    public Optional<Asset> findAssetById(String id) {

        Asset asset = new Asset();
        return Optional.of(asset);
    }

    // TODO Implement and observer for asset of type vehicle insertion to create a maintenance schedule.
    @Override
    public List<Asset> findAssetsByAssigneeID(String staffID) {

        List<Asset> assets = new ArrayList<>();

        return assets;
    }
}
