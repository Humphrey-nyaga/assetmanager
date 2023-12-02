package com.assetmanager.app.observer;

import com.assetmanager.app.mail.bean.MailBeanI;
import com.assetmanager.app.mail.model.Mail;
import com.assetmanager.app.mail.utility.MailFormatter;
import com.assetmanager.app.model.entity.User;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
@ApplicationScoped
public class UserHandler {
    @EJB
    MailBeanI mailBean;
    @Inject
    MailFormatter mailFormat;

    public void afterUserCreate(@Observes @Created User user) {
        String subject = "RE: Account Creation Confirmation" ;
        String message = "Dear " + user.getUsername() + ", <br>" +
                "Thank you for registering with us 🎉. <br> Your account has been created in the Asset Management System." +
                "<br> Your username is: <b>" + user.getUsername() +
                "</b>.<br> Kindly use it to login. <br>" +
                "<br>" +
                "Kind Regards, <br> Asset Management Team <br> ✨";

        String templateContent = mailFormat.emailTemplate();
        String fomattedMessage = templateContent.replace("%body%", message);

        System.out.println("Mail Content " + fomattedMessage);

        Mail mail = new Mail();
        mail.setRecipient(user.getEmail());
        mail.setSubject(subject);
        mail.setMessage(fomattedMessage);
        mailBean.sendMail(mail);
        System.out.println("Mail Sent Successfully");
    }


}
