package com.eventchat.entity;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mId = null;
    private String mTitle = null;
    private String mType = null;
    private String mBody = null;
    private String mCreatedAt = null;
    private User mAuthor = null;
    private List<Comment> mCommentList = null;
    private List<User> mLikedUserList = null;

    public Post() {

    }

    public Post(String id, String title, String type, String body,
            String createdAt, User author, List<Comment> commentList,
            List<User> likedUserList) {
        mId = id;
        mTitle = title;
        mType = type;
        mBody = body;
        mCreatedAt = createdAt;
        mAuthor = author;
        mCommentList = commentList;
        mLikedUserList = likedUserList;
    }

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

    public List<Comment> getCommentList() {
        return mCommentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.mCommentList = commentList;
    }

    public List<User> getLikedUserList() {
        return mLikedUserList;
    }

    public void setLikedUserList(List<User> likedUserList) {
        this.mLikedUserList = likedUserList;
    }
}
