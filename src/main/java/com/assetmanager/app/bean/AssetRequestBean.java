package com.assetmanager.app.bean;

import com.assetmanager.app.mail.bean.MailBeanI;
import com.assetmanager.app.mail.model.Mail;
import com.assetmanager.app.mail.utility.MailFormatter;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.util.SerialIDGenerator.SerialIDGenerator;
import com.assetmanager.util.idgenerator.GenericIDGenerator;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Stateless
@Remote
public class AssetRequestBean extends GenericBean<AssetRequest> implements AssetRequestBeanI {
    @EJB
    MailBeanI mailBean;
    @EJB
    AssigneeBeanI assigneeBean;
    @Inject
    MailFormatter mailFormatter;
//    @Inject
//    GenericIDGenerator assetRequestId;

    @Inject
    @Named("RequestID")
    private SerialIDGenerator serialIDGenerator;

    @Override
    public void create(AssetRequest entity) {
        try {
            Optional<Assignee> assignee = assigneeBean.getAssigneeByStaffId(entity.getStaffId());
            StringBuilder message = new StringBuilder();

            if (assignee.isPresent()) {
                entity.setAssetRequestID(serialIDGenerator.generate());

                getDao().create(entity);
                Assignee assignee1 = assignee.get();

                String subject = "RE: Asset Request Received";
                message.append("Dear ").append(assignee1.getFirstName()).append(", <br> we are pleased to inform you that ")
                        .append(" your request for ")
                        .append(entity.getQuantity()).append(" ")
                        .append(entity.getAssetName()).append(" ")
                        .append(entity.getDescription())
                        .append(" has been received and is being reviewed. <br> " +
                                "The status will be communicated soon <br> ").
                        append("<br> Kind Regards, <br> Humphrey G, <br> Managing Director ASM ");

                String htmlContent = mailFormatter.emailTemplate();
                String fomattedMessage = htmlContent.replace("%body%", message.toString());

                System.out.println("Mail Content " + fomattedMessage);

                Mail mail = new Mail();
                mail.setRecipient(assignee1.getEmail());
                mail.setSubject(subject);
                mail.setMessage(fomattedMessage);
                //mailBean.sendMail(mail);
                System.out.println("Mail Set Successfully");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


}
