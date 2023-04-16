package com.example.pairresearch.models.preferences;

import java.util.Date;

public class RangePreference extends Preference{

    private double from;
    private double to;

    public RangePreference(){}

    public RangePreference(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public RangePreference(int preferenceId, int userID, Date time, double from, double to) {
        super(preferenceId, userID, time);
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }
}
