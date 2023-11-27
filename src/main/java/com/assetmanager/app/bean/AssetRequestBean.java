package com.assetmanager.app.bean;

import com.assetmanager.app.dao.GenericDao;
import com.assetmanager.app.dao.GenericDaoI;
import com.assetmanager.app.mail.bean.MailBeanI;
import com.assetmanager.app.mail.model.Mail;
import com.assetmanager.app.mail.utility.MailFormatter;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Assignee;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    @Override
    public void create(AssetRequest entity) {
        try {

        getDao().create(entity);

        Optional<Assignee> assignee = assigneeBean.getAssigneeByStaffId(entity.getStaffId());
        StringBuilder message = new StringBuilder().append("Dear ");
            if (assignee.isPresent()) {
                Assignee assignee1 = assignee.get();
                String subject = "RE: Asset Request Received";
                message.append(assignee1.getFirstName()).append(", <br> we are pleased to inform tou that ")
                        .append(" your request for ")
                        .append(entity.getQuantity()).append(" ")
                        .append(entity.getAssetName()).append(" ")
                        .append(entity.getDescription())
                        .append(" has been received and is being reviewed. <br> " +
                                "The status will be communicated soon <br> ").
                        append("\n Kind Regards, <br> Humphrey, <br> Managing Director");

                 String htmlContent = mailFormatter.EmailTemplate();
                 String fomattedMessage = htmlContent.replace("%body%",message.toString());

                System.out.println("Mail Content " + fomattedMessage);

                Mail mail = new Mail();
                mail.setRecipient(assignee1.getEmail());
                mail.setSubject(subject);
                mail.setMessage(fomattedMessage);
                mailBean.sendMail(mail);
                System.out.println("Mail Set Successfully");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


}
