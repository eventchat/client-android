package com.eventchat.entity;

public class Chat {

    private String mTo = null;
    private String mMessage = null;

    public String getTo() {
        return mTo;
    }

    public void setTo(String to) {
        this.mTo = to;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }
}
