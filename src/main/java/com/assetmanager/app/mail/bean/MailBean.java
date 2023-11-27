package com.assetmanager.app.mail.bean;

import com.assetmanager.app.mail.model.Mail;
import io.github.cdimascio.dotenv.Dotenv;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

@Stateless
public class MailBean implements MailBeanI, Serializable {
    private int port = 465;
    private final String host = "smtp.gmail.com";

    private final String from = System.getProperty("FROM");
    private boolean auth = true;
    private  Session session;
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
            message.setRecipients(Message.RecipientType.TO, address);
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
