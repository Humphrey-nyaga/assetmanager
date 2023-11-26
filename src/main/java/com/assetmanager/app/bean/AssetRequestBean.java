package com.assetmanager.app.bean;

import com.assetmanager.app.dao.GenericDao;
import com.assetmanager.app.dao.GenericDaoI;
import com.assetmanager.app.mail.bean.MailBeanI;
import com.assetmanager.app.mail.model.Mail;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Assignee;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Optional;

@Stateless
@Local
public class AssetRequestBean extends GenericBean<AssetRequest> implements AssetRequestBeanI {
    @EJB
    MailBeanI mailBean;
    @EJB
    AssigneeBeanI assigneeBean;

    @Override
    public void create(AssetRequest entity) {
        try {

        getDao().create(entity);

        Optional<Assignee> assignee = assigneeBean.getAssigneeByStaffId(entity.getStaffId());
        StringBuilder message = new StringBuilder().append("Dear ");
            if (assignee.isPresent()) {
                Assignee assignee1 = assignee.get();
                String subject = "RE: Asset Request Received";
                message.append(assignee1.getFirstName()).append(", \n")
                        .append(" Your request for ")
                        .append(entity.getQuantity()).append(" ")
                        .append(entity.getAssetName()).append(" ")
                        .append(entity.getDescription())
                        .append(" has been received and is being reviewed.\n " +
                                "The status will be communicated soon ").
                        append("\n Regards, Humphrey \n Managing Director");

                System.out.println("Creating Mail object and sending email...");
                Mail mail = new Mail();
                mail.setRecipient(assignee1.getEmail());
                mail.setSubject(subject);
                mail.setMessage(message.toString());
                mailBean.sendMail(mail);
                System.out.println("Mail Set Successfully");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


}
