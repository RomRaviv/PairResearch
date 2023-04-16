package com.example.pairresearch.models;

import com.example.pairresearch.models.enums.UserType;

import java.util.Objects;

public class MediaUpload{
    private int id;
    private UserType userType;
    private int foreignId;
    private String mediaSource;
    private boolean active;

    public MediaUpload() {
    }

    public void setMediaSource(String mediaSource) {
        this.mediaSource = mediaSource;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public UserType getUserType() {
        return userType;
    }

    public int getForeignId() {
        return foreignId;
    }

    public String getMediaSource() {
        return mediaSource;
    }

    public boolean isActive() {
        return active;
    }

    public MediaUpload(int id, UserType userType, int foreignId, String mediaSource, boolean active) {
        this.id = id;
        this.userType = userType;
        this.foreignId = foreignId;
        this.mediaSource = mediaSource;
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediaUpload)) return false;
        MediaUpload that = (MediaUpload) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
