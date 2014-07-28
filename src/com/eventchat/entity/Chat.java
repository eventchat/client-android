package com.eventchat.entity;

import java.io.Serializable;

public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mTo = null;
    private String mMessage = null;

    public Chat() {

    }

    public Chat(String to, String message) {
        mTo = to;
        mMessage = message;
    }

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
