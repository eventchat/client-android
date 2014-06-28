package com.eventchat.entity;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class Session implements IParameterize {

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

    @Override
    public HttpParams toParams() {
        HttpParams params = new BasicHttpParams();
        return params;
    }
}
