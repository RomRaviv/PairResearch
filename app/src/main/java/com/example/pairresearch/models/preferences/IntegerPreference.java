package com.example.pairresearch.models.preferences;

import java.util.Date;

public class IntegerPreference extends Preference{
    private int value;

    public IntegerPreference(){}

    public IntegerPreference(int value) {
        this.value = value;
    }

    public IntegerPreference(int preferenceId, int userID, Date time, int value) {
        super(preferenceId, userID, time);
        this.value = value;
    }
}
