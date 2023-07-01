package com.example.pairresearch.models;

import java.util.ArrayList;

public class Qualifications {
    public long listed;
    public ArrayList<String> other;

    public Qualifications(){}

    public Qualifications(long listed, ArrayList<String> other) {
        this.listed = listed;
        this.other = other;
    }

    public long getListed() {
        return listed;
    }

    public void setListed(long listed) {
        this.listed = listed;
    }

    public ArrayList<String> getOther() {
        return other;
    }

    public void setOther(ArrayList<String> other) {
        this.other = other;
    }

    public void addQualification(String qualification){
        other.add(qualification);
    }

}
