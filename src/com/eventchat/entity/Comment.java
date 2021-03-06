package com.eventchat.entity;

import java.io.Serializable;

public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mId = null;
    private User mAuthor = null;
    private String mBody = null;
    private String mCreatedAt = null;

    public Comment() {

    }

    public Comment(String id, User author, String body, String createdAt) {
        mId = id;
        mAuthor = author;
        mBody = body;
        mCreatedAt = createdAt;
    }

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
}
