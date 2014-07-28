package com.eventchat.entity;

import java.io.Serializable;

public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mUserName = null;
    private String mPassword = null;
    private boolean mLoggedIn = false;

    public Session() {

    }

    public Session(String name, String password) {
        mUserName = name;
        mPassword = password;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public boolean isLoggedIn() {
        return mLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.mLoggedIn = loggedIn;
    }
}
