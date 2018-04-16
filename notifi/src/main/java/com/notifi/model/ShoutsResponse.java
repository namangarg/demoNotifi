package com.notifi.model;

public class ShoutsResponse {


    private int shoutId;
    private int receiverId;
    private int senderId;
    private boolean isForwarded;
    private int isStarMarked;

    public int getShoutId() {
        return shoutId;
    }

    public void setShoutId(int shoutId) {
        this.shoutId = shoutId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public boolean isForwarded() {
        return isForwarded;
    }

    public void setForwarded(boolean forwarded) {
        isForwarded = forwarded;
    }

    public int getIsStarMarked() {
        return isStarMarked;
    }

    public void setIsStarMarked(int isStarMarked) {
        this.isStarMarked = isStarMarked;
    }

}
