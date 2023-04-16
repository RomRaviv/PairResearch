package com.example.pairresearch.models.preferences;

import java.util.Date;

public class Preference {

    protected int preferenceId;
    protected int userID;
    protected Date time;

    public Preference(){}

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Preference(int preferenceId, int userID, Date time) {
        this.preferenceId = preferenceId;
        this.userID = userID;
        this.time = time;
    }
}
