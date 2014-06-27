package com.eventchat.entity;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class Comment implements IParameterize {

    private String mId;
    private User mAuthor;
    private String mBody;
    private String mCreatedAt;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public User getAuthor() {
        return mAuthor;
    }

    public void setAuthor(User author) {
        this.mAuthor = author;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        this.mBody = body;
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
