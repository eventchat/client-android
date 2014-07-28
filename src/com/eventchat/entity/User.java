package com.eventchat.entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mId = null;
    private String mName = null;
    private String mEmail = null;
    private String mInfo = null;
    private String mAvatarUrl = null;

    public User() {

    }

    public User(String id, String name, String email, String info, String url) {
        mId = id;
        mName = name;
        mEmail = email;
        mInfo = info;
        mAvatarUrl = url;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        this.mInfo = info;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.mAvatarUrl = avatarUrl;
    }
}
