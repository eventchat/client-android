package com.eventchat.entity;

import java.io.Serializable;

public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private User mFrom = null;

    private User mTo = null;

    private String mMessage = null;

    private String mCreatedAt = null;

    public ChatMessage(User from, User to, String message, String createdAt) {
        mFrom = from;
        mTo = to;
        mMessage = message;
        mCreatedAt = createdAt;
    }

    public User getFrom() {
        return mFrom;
    }

    public void setFrom(User from) {
        this.mFrom = from;
    }

    public User getTo() {
        return mTo;
    }

    public void setTo(User to) {
        mTo = to;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    public String getCreatedTime() {
        return mCreatedAt;
    }

    public void setCreatedTime(String createdAt) {
        mCreatedAt = createdAt;
    }
}
