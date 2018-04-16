package com.notifi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Shout {

    private int shoutId;
    private User user;
    private String title;
    private int tag;
    private String description;
    private float latitude;
    private float longitude;
    private String landmark;
    private boolean displayGuest;
    private boolean canForward;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private java.sql.Timestamp startTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private java.sql.Timestamp endTime;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private java.sql.Timestamp rsvpTime;

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getRsvpTime() {
        return rsvpTime;
    }

    public void setRsvpTime(Timestamp rsvpTime) {
        this.rsvpTime = rsvpTime;
    }

    public boolean isDisplayGuest() {
        return displayGuest;
    }

    public void setDisplayGuest(boolean displayGuest) {
        this.displayGuest = displayGuest;
    }


    public boolean isCanForward() {
        return canForward;
    }

    public void setCanForward(boolean canForward) {
        this.canForward = canForward;
    }

    public int getShoutId() {
        return shoutId;
    }

    public void setShoutId(int shoutId) {
        this.shoutId = shoutId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
