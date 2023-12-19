package com.assetmanager.app.observer;

import com.assetmanager.app.dto.AssetAssignAction;
import com.assetmanager.app.mail.bean.MailBeanI;
import com.assetmanager.app.mail.model.Mail;
import com.assetmanager.app.mail.utility.MailFormatter;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.AssetAssignmentLog;
import com.assetmanager.app.model.entity.Assignee;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class AssetAssigmentEventHandler {

    @EJB
    MailBeanI mailBean;

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    MailFormatter mailFormatter;

    public void afterAssetAssignedSendEmail(@ObservesAsync @Assigned AssetAssignmentEvent assetAssignmentEvent) {
        Asset asset = assetAssignmentEvent.asset();
        Assignee assignee = assetAssignmentEvent.assignee();

        String subject = "RE: Asset Assigned Notification";
        String message = "Dear " + assignee.getFirstName() + ", <br> we are pleased to inform you that " +
                " an asset of Serial Number " +
                asset.getSerialNumber() + ", Name: " +
                asset.getName() + " and Type: " +
                asset.getCategory().getName() +
                " has been officially assigned to you as the custodian. <br> " +
                "<br> " +
                "<br> Kind Regards, <br> Humphrey , <br> Managing Director ASM ";

        String htmlContent = mailFormatter.emailTemplate();
        String fomattedMessage = htmlContent.replace("%body%", message);

        System.out.println("Mail Content " + fomattedMessage);

        Mail mail = new Mail();
        mail.setRecipient(assignee.getEmail());
        mail.setSubject(subject);
        mail.setMessage(fomattedMessage);
        mailBean.sendMail(mail);
    }

    public void afterAssignedLogToDatabase(@Observes @Assigned AssetAssignmentEvent assetAssignmentEvent) {
        handleAssignmentEvent(assetAssignmentEvent);
    }

    public void afterUnassignedLogToDatabase(@Observes @UnAssigned AssetAssignmentEvent assetAssignmentEvent) {
        handleAssignmentEvent(assetAssignmentEvent);
    }

    private void handleAssignmentEvent(AssetAssignmentEvent assetAssignmentEvent) {
        Asset asset = assetAssignmentEvent.asset();
        Assignee assignee = assetAssignmentEvent.assignee();
        AssetAssignAction assignAction = assetAssignmentEvent.assetAssignAction();

        //TODO - Probably do some cleanup later here
        AssetAssignmentLog assetAssignmentLog = new AssetAssignmentLog();
        assetAssignmentLog.setAssignAssetAction(assignAction.name());
        assetAssignmentLog.setCategory(asset.getCategory().name());
        assetAssignmentLog.setAssigneeId(assignee.getId());
        assetAssignmentLog.setStaffNumber(assignee.getStaffNumber());
        assetAssignmentLog.setSerialNumber(asset.getSerialNumber());

        entityManager.merge(assetAssignmentLog);
        System.out.println("Asset Assignment Logged into the Database!!");
    }


    public void afterAssetUnAssignmentSendEmail(@ObservesAsync @UnAssigned AssetAssignmentEvent ase) {
        Asset asset = ase.asset();
        Assignee assignee = ase.assignee();

        String subject = "RE: Asset Unassigned Notification";
        String message = "Dear " + ase.assignee().getFirstName() + ", <br> we would like notify you that " +
                " the asset of Serial Number " +
                ase.asset().getSerialNumber() + ", Name: " +
                ase.asset().getName() + " of Type: " +
                ase.asset().getCategory().getName() +
                " has been officially unassigned from you as the custodian. <br> " +
                "Thank You For Taking Good Care of It." +
                "<br> " +
                "<br> Kind Regards, <br> Humphrey , <br> Managing Director ASM ";

        String htmlContent = mailFormatter.emailTemplate();
        String fomattedMessage = htmlContent.replace("%body%", message);

        System.out.println("Mail Content " + fomattedMessage);

        Mail mail = new Mail();
        mail.setRecipient(assignee.getEmail());
        mail.setSubject(subject);
        mail.setMessage(fomattedMessage);
        mailBean.sendMail(mail);
    }

}
