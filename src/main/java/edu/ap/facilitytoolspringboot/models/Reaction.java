package edu.ap.facilitytoolspringboot.models;

import java.util.Date;

public class Reaction {

    private String messageId;
    private String name;
    private String message;
    private Date date;

    public Reaction() {
    }

    public Reaction(String messageId, String name, String message) {
        this.messageId = messageId;
        this.name = name;
        this.message = message;
    }

    public Reaction(String messageId, String name, String message, Date date) {
        this.messageId = messageId;
        this.name = name;
        this.message = message;
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" + " messageId='" + getMessageId() + "'" + ", name='" + getName() + "'" + ", message='" + getMessage()
                + "'" + ", datum='" + getDate() + "'" + "}";
    }

}