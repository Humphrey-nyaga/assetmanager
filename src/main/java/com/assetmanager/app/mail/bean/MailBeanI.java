package com.assetmanager.app.mail.bean;

import com.assetmanager.app.mail.model.Mail;

import javax.ejb.Local;
import javax.ejb.Remote;

public interface MailBeanI {

    void sendMail(Mail mail);
}
