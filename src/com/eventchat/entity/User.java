package com.eventchat.entity;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import com.eventchat.util.Constant;

public class User implements IParameterize {

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

    @Override
    public HttpParams toParams() {
        HttpParams params = new BasicHttpParams();
        params.setParameter(Constant.Common.ID, mId);
        params.setParameter(Constant.Common.NAME, mName);
        params.setParameter(Constant.User.EMAIL, mEmail);
        params.setParameter(Constant.User.INFO, mInfo);
        params.setParameter(Constant.User.AVATAR_URL, mAvatarUrl);
        return params;
    }
}
