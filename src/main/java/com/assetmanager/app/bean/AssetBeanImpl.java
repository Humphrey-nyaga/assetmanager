package com.assetmanager.app.bean;

import com.assetmanager.app.dto.AssetDTO;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.service.AssetsValuationI;
import com.assetmanager.util.idgenerator.GenericIDGenerator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class AssetBeanImpl extends GenericBean<Asset> implements AssetBeanI {

    @Inject
    GenericIDGenerator generate;

    @PersistenceContext
    EntityManager em;

    @Inject
    AssetsValuationI assetsValuation;

    @Override
    @SuppressWarnings("unchecked")
    public List<Asset> list(Object entity) {
        String jpql = "FROM " + entity.getClass().getName() + " e ORDER BY e.createdAt DESC";
        return em.createQuery(jpql, Asset.class)
                .getResultList();
    }

    @Override
    public Asset findAssetBySerialNumber(String serialNumber, Object entity) {
        String jpql = "FROM  " + entity.getClass().getName() + " e WHERE e.serialNumber=:serialNumber";
        return em.createQuery(jpql, Asset.class).setParameter("serialNumber",serialNumber)
                .getSingleResult();
    }

    // TODO Implement and observer for asset of type vehicle insertion to create a maintenance schedule.
    @Override
    public List<Asset> findAssetsByAssigneeID(Long assigneeID) {
        String jpql = "FROM Asset a WHERE a.assigneeID=:assigneeID";
        return em.createQuery(jpql, Asset.class).setParameter("assigneeID",assigneeID)
                .getResultList();
    }

    @Override
    public Map<String, String> assetsValueByCategory(){
        return assetsValuation.totalAssetValueByCategory(list(new Asset()));
    }

    @Override
    public void deleteBySerialNumber(Object entity, String serialNumber) {
        Asset asset = findAssetBySerialNumber(serialNumber, entity);
        em.remove(asset);
    }

    // SELECT a.serialNumber, a.name FROM Asset a

    @Override
    public List<AssetDTO> findAllAssetsNameAndSerialNo() {
        List<Asset> assets  = list(new Asset());
       return  assets.stream()
                .map(asset -> new AssetDTO(asset.getSerialNumber(), asset.getName()))
                .collect(Collectors.toList());
    }
}
