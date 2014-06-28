package com.eventchat.entity;

import java.util.List;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import com.eventchat.util.Constant;

public class Post implements IParameterize {

    private String mId = null;
    private String mTitle = null;
    private String mType = null;
    private String mBody = null;
    private String mCreatedAt = null;
    private User mAuthor = null;
    private List<Comment> mComments = null;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
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

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.mCreatedAt = createdAt;
    }

    public User getAuthor() {
        return mAuthor;
    }

    public void setAuthor(User author) {
        this.mAuthor = author;
    }

    public List<Comment> getComments() {
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        this.mComments = comments;
    }

    @Override
    public HttpParams toParams() {
        HttpParams params = new BasicHttpParams();
        params.setParameter(Constant.Common.ID, mId);
        params.setParameter(Constant.Post.TITLE, mTitle);
        params.setParameter(Constant.Post.TYPE, mType);
        params.setParameter(Constant.Common.BODY, mBody);
        params.setParameter(Constant.Common.CREATED_AT, mCreatedAt);
        return params;
    }
}
