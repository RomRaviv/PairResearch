package com.example.pairresearch.models.preferences;

import java.util.Date;

public class BooleanPreference extends Preference{
    private boolean value;

    public BooleanPreference(){}

    public BooleanPreference(int preferenceId, int userID, Date time, boolean value) {
        super(preferenceId, userID, time);
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
