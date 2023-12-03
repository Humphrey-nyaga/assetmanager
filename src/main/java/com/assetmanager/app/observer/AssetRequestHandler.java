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
        StringBuilder message = new StringBuilder()
                .append("Dear ").append(assignee.getFirstName()).append(", <br> we are pleased to inform you that ")
                .append(" your request for ")
                .append(assetRequest.getQuantity()).append(" ")
                .append(assetRequest.getAssetName()).append(" ")
                .append(assetRequest.getDescription())
                .append(" has been received and is being reviewed. <br> " +
                        "The status will be communicated soon <br> ").
                append("<br> Kind Regards, <br> Humphrey G, <br> Managing Director ASM ");

        String htmlContent = mailFormat.emailTemplate();
        String fomattedMessage = htmlContent.replace("%body%", message.toString());

        System.out.println("Mail Content " + fomattedMessage);

        Mail mail = new Mail();
        mail.setRecipient(assignee.getEmail());
        mail.setSubject(subject);
        mail.setMessage(fomattedMessage);
        mailBean.sendMail(mail);
        System.out.println("Mail Set Successfully");
    }

}
