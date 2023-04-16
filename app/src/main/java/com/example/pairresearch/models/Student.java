package com.example.pairresearch.models;

import com.example.pairresearch.models.preferences.Preference;

import java.util.ArrayList;
import java.util.Date;

public class Student extends User{

    private String university;
    private String yearOfStudy;
    private String qualifications;
    private ArrayList<Preference> preferences;

    public Student(String id, String name, String email, String description, String password, String linkedin, Date registrationDate, long phoneNumber, boolean active, String university, String yearOfStudy, String qualifications) {
        super(id, name, email, description, password, linkedin, registrationDate, phoneNumber, active);
        this.university = university;
        this.yearOfStudy = yearOfStudy;
        this.qualifications = qualifications;
        this.preferences = new ArrayList<>();
    }

    public Student(String university, String yearOfStudy, String qualifications) {
        this.university = university;
        this.yearOfStudy = yearOfStudy;
        this.qualifications = qualifications;
        this.preferences = new ArrayList<>();
    }

    public Student(){}


}
