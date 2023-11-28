package com.assetmanager.app.mail.bean;

import com.assetmanager.app.mail.model.Mail;

import javax.ejb.Local;

@Local
public interface MailBeanI {

    void sendMail(Mail mail);
}
