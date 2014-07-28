package com.eventchat.entity;

import java.io.Serializable;

public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mId = null;
    private String mName = null;
    private double mLongitude = 0.0;
    private double mLatitude = 0.0;
    private String mAddress = null;
    private String mStartTime = null;
    private String mEndTime = null;
    private String mDesc = null;
    private String mOrganizer = null;

    public Event() {

    }

    public Event(String id, String name, double longitude, double latitude,
            String address, String startTime, String endTime, String desc,
            String organizer) {
        mId = id;
        mName = name;
        mLongitude = longitude;
        mLatitude = latitude;
        mAddress = address;
        mStartTime = startTime;
        mEndTime = endTime;
        mDesc = desc;
        mOrganizer = organizer;
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

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
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

    public String getOrganizer() {
        return mOrganizer;
    }

    public void setOrganizer(String organizer) {
        this.mOrganizer = organizer;
    }
}
