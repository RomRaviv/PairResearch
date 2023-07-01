package com.example.pairresearch.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class User {

    protected int id;
    protected String name;
    protected String email;
    protected String description;
    protected String password;
    protected String linkedin;
    protected long phoneNumber;
    protected ArrayList<MediaUpload> mediaUploads;
    protected Date registrationDate;
    protected boolean active;

    public User(int id, String name, String email, String description, String password, String linkedin, Date registrationDate, long phoneNumber, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.description = description;
        this.password = password;
        this.linkedin = linkedin;
        this.mediaUploads = new ArrayList<>();
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }

    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setUserDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return active;
    }

    public int getId() {
        return id;
    }

    public ArrayList<MediaUpload> getMediaUploads() {
        return mediaUploads;
    }

    public boolean addMediaUpload(MediaUpload mediaUpload) {
        return  mediaUploads.add(mediaUpload);
    }

    public boolean removeMediaUpload(MediaUpload mediaUpload) {
        return mediaUploads.remove(mediaUpload);
    }

    public ArrayList<String> getAllMediaSources(){
        ArrayList<String> retVal = new ArrayList<>();
        for(MediaUpload media : mediaUploads){
            retVal.add(media.getMediaSource());
        }
        return retVal;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
