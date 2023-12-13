package com.assetmanager.app.bean;

import com.assetmanager.app.dto.AssetAssignAction;
import com.assetmanager.app.dto.AssetDTO;
import com.assetmanager.app.dto.AssignAssetDTO;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.app.observer.*;
import com.assetmanager.app.service.AssetsValuationI;
import com.assetmanager.exceptions.AssetAlreadyAssignedException;
import com.assetmanager.util.idgenerator.GenericIDGenerator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
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

    @EJB
    AssigneeBeanI assigneeBean;

    @Inject
    @Assigned
    private Event<AssetAssignmentEvent> assignAssetEvent;

    @Inject
    @UnAssigned
    private Event<AssetAssignmentEvent> unassignAssetEvent;


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
        return em.createQuery(jpql, Asset.class).setParameter("serialNumber", serialNumber)
                .getSingleResult();
    }

    // TODO Implement and observer for asset of type vehicle insertion to create a maintenance schedule.
    @Override
    public List<Asset> findAssetsByAssigneeID(Long assigneeID) {
        String jpql = "FROM Asset a WHERE a.assigneeID=:assigneeID";
        return em.createQuery(jpql, Asset.class).setParameter("assigneeID", assigneeID)
                .getResultList();
    }

    @Override
    public Map<String, String> assetsValueByCategory() {
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
        List<Asset> assets = list(new Asset());
        return assets.stream()
                .map(asset -> new AssetDTO(asset.getSerialNumber(), asset.getName(),asset.getAssigneeId()))
                .collect(Collectors.toList());
    }

    @Override
    public void assignAssetToAssignee(AssignAssetDTO assignAssetDTO) {
        Asset asset = findAssetBySerialNumber(assignAssetDTO.getAssetSerialId(), new Asset());
        System.out.println(">>>>>>>>ASSET" + asset);
        System.out.println("ASSET ID = " + asset.getId());
        Assignee assignee = assigneeBean.findById(Assignee.class, Long.valueOf(assignAssetDTO.getAssigneeId()));
        System.out.println(">>>>>>>>ASSIGNEE" + assignee);
        System.out.println(">>>>>>>>ASSIGNEE " + assignee.getId());


        try {

            if (assignAssetDTO.getAssignaction().equals(AssetAssignAction.UNASSIGN)) {

                System.out.println("DTO Assignee ID >>" + assignAssetDTO.getAssigneeId() + "  Asset Assigne" + asset.getAssigneeId());
                if (asset.getAssigneeId() != Long.valueOf(assignAssetDTO.getAssigneeId())) {
                    assignee = assigneeBean.findById(Assignee.class, asset.getAssigneeId());
                } else {
                    System.out.println("Ids Match Correctly");
                }
                asset.setAssignee(null);
                getDao().addOrUpdate(asset);

                AssetAssignmentEvent assetAssignmentEvent = new AssetAssignmentEvent(asset, assignee, assignAssetDTO.getAssignaction());
                unassignAssetEvent.fire(assetAssignmentEvent);
            }

            if (!isAssetAssigned(asset)) {
                if (assignAssetDTO.getAssignaction().equals(AssetAssignAction.ASSIGN)) {
                    asset.setAssignee(assignee);
                    getDao().addOrUpdate(asset);

                    AssetAssignmentEvent assetAssignmentEvent = new AssetAssignmentEvent(asset, assignee, assignAssetDTO.getAssignaction());
                    assignAssetEvent.fire(assetAssignmentEvent);
                }
            }

            throw new AssetAlreadyAssignedException("Failed!. Asset is already Assigned!");

        } catch (AssetAlreadyAssignedException ex) {
            System.out.println("Error + " + ex.getMessage());
        }
    }

    private Boolean isAssetAssigned(Asset asset) {
        return asset.getAssigneeId() != null;
    }
}
