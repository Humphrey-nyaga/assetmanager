package com.assetmanager.app.mail.model;

import java.io.Serializable;

public class Mail implements Serializable {
    private String recipient;
    private String subject;
    private String message;

    public Mail() {
    }


    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
