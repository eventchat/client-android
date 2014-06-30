package com.eventchat.entity;

public class Comment {

    private String mId = null;
    private User mAuthor = null;
    private String mBody = null;
    private String mCreatedAt = null;

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
