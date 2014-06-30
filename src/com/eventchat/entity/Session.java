package com.eventchat.entity;

public class Session {

    private String mUserName = null;
    private String mPassword = null;
    private boolean mLoggedIn = false;

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
