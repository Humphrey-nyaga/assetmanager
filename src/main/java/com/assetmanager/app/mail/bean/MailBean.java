package com.assetmanager.app.mail.bean;

import com.assetmanager.app.mail.model.Mail;

import javax.ejb.Singleton;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

@Singleton
public class MailBean implements MailBeanI, Serializable {
    private final int port = 465;
    private final String host = "smtp.gmail.com";

    private final String from = System.getProperty("FROM");
    private final boolean auth = true;
    private final Session session;
    private final String password = System.getProperty("PASSWORD");
    private final String username = System.getProperty("USERNAME");
    private final boolean debug = true;

    public MailBean(){

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", auth);

        props.put("mail.smtp.ssl.enable", true);

            this.session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            session.setDebug(debug);

    }
    @Override
    public void sendMail(Mail mail) {

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(mail.getRecipient())};
            InternetAddress[] replyToEmail = {new InternetAddress(from)};

            message.setRecipients(Message.RecipientType.TO, address);
            message.setReplyTo(replyToEmail);
            message.setSubject(mail.getSubject());
            message.setSentDate(new Date());
            message.setContent(mail.getMessage(),"text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

    }
}
