package com.dailycodebuffer.mq;

import java.util.Date;


public class CustomMessage {

    private String messageId;
    private String message;
    private String authorID;
    private String authorName;
    private Date messageDate;

    public CustomMessage(String messageId, String message, String authorID, String authorName, Date messageDate) {
        this.messageId = messageId;
        this.message = message;
        this.authorID = authorID;
        this.authorName = authorName;
        this.messageDate = messageDate;
    }

    public CustomMessage() {
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "messageId='" + messageId + '\'' +
                ", message='" + message + '\'' +
                ", authorID='" + authorID + '\'' +
                ", authorName='" + authorName + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}
