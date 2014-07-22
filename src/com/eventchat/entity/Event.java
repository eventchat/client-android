package com.eventchat.entity;

import java.util.List;

public class Event {

    private String mId = null;
    private String mName = null;
    private double mLongitude = 0.0;
    private double mLatitude = 0.0;
    private String mStartTime = null;
    private String mEndTime = null;
    private String mDesc = null;
    private List<User> mAttendeeList = null;

    public List<User> getAttendeeList() {
        return mAttendeeList;
    }

    public void setAttendeeList(List<User> attendeeList) {
        this.mAttendeeList = attendeeList;
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

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        this.mStartTime = startTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        this.mEndTime = endTime;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        this.mDesc = desc;
    }
}
