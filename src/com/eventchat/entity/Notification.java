package com.eventchat.entity;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class Notification implements IParameterize {

    private String mId = null;
    private String mType = null;
    private String mBody = null;
    private boolean mIsRead = false;
    private String mCreatedAt = null;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        this.mBody = body;
    }

    public boolean isRead() {
        return mIsRead;
    }

    public void setRead(boolean isRead) {
        this.mIsRead = isRead;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.mCreatedAt = createdAt;
    }

    @Override
    public HttpParams toParams() {
        HttpParams params = new BasicHttpParams();
        return params;
    }
}
