package com.assetmanager.app.bean;

import com.assetmanager.app.mail.bean.MailBeanI;
import com.assetmanager.app.mail.utility.MailFormatter;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.app.observer.AssetRequestEvent;
import com.assetmanager.app.observer.Created;
import com.assetmanager.util.SerialIDGenerator.SerialIDGenerator;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
@Local
public class AssetRequestBean extends GenericBean<AssetRequest> implements AssetRequestBeanI {
    @EJB
    MailBeanI mailBean;
    @EJB
    AssigneeBeanI assigneeBean;
    @Inject
    MailFormatter mailFormatter;
//    @Inject
//    GenericIDGenerator assetRequestId;

    @PersistenceContext
    EntityManager em;

    @Inject
    @Named("RequestID")
    private SerialIDGenerator serialIDGenerator;

    @Inject
    @Created
    private Event<AssetRequestEvent> assetRequestEvent;

    @Override
    public void addOrUpdate(AssetRequest assetRequest) {
        try {
            Optional<Assignee> assignee = assigneeBean.getAssigneeByStaffId(assetRequest.getStaffId());

            if (assignee.isPresent()) {
                assetRequest.setAssetRequestID(serialIDGenerator.generate());
                getDao().addOrUpdate(assetRequest);
                Assignee assignee1 = assignee.get();
                assetRequestEvent.fire(new AssetRequestEvent(assetRequest, assignee1));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @Override
    public AssetRequest getRequest(Long id) {
        AssetRequest assetRequest =  em.find(AssetRequest.class, id);
        return assetRequest;
    }
}
