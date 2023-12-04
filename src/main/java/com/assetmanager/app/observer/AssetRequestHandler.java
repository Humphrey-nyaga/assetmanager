package com.assetmanager.app.observer;

import com.assetmanager.app.mail.bean.MailBeanI;
import com.assetmanager.app.mail.model.Mail;
import com.assetmanager.app.mail.utility.MailFormatter;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Assignee;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class AssetRequestHandler {
    @EJB
    MailBeanI mailBean;
    @Inject
    MailFormatter mailFormat;

    public void afterRequestCreated(@Observes @Created AssetRequestEvent assetRequestEvent) {
        AssetRequest assetRequest = assetRequestEvent.assetRequest();
        Assignee assignee = assetRequestEvent.assignee();

        String subject = "RE: Asset Request Received";
        String message = "Dear " + assignee.getFirstName() + ", <br> we are pleased to inform you that " +
                " your request for " +
                assetRequest.getQuantity() + " " +
                assetRequest.getAssetName() + " " +
                assetRequest.getDescription() +
                " has been received and is being reviewed. <br> " +
                "The status will be communicated soon <br> " +
                "<br> Kind Regards, <br> Humphrey G, <br> Managing Director ASM ";

        String htmlContent = mailFormat.emailTemplate();
        String fomattedMessage = htmlContent.replace("%body%", message);

        System.out.println("Mail Content " + fomattedMessage);

        Mail mail = new Mail();
        mail.setRecipient(assignee.getEmail());
        mail.setSubject(subject);
        mail.setMessage(fomattedMessage);
        mailBean.sendMail(mail);
        System.out.println("Mail Set Successfully");
    }

}
