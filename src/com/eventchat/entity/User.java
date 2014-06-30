package com.eventchat.entity;

public class User {

    private String mId = null;
    private String mName = null;
    private String mEmail = null;
    private String mInfo = null;
    private String mAvatarUrl = null;

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
