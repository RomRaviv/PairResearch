package com.example.pairresearch.models;

import com.example.pairresearch.App;
import com.example.pairresearch.models.preferences.Preference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student extends User{

    private String university;
    private String yearOfStudy;
    private Qualifications qualifications;
    private ArrayList<Preference> preferences;
    private ArrayList<Research> researchMatches;


    public Student(int id, String name, String email, String description, String password, String linkedin, Date registrationDate, String phoneNumber, boolean active, String university, String yearOfStudy, Qualifications qualifications) {
        super(id, name, email, description, password, linkedin, registrationDate, phoneNumber, active);
        this.university = university;
        this.yearOfStudy = yearOfStudy;
        this.qualifications = qualifications;
        this.preferences = new ArrayList<>();
    }

    public Student(String university, String yearOfStudy, Qualifications qualifications) {
        this.university = university;
        this.yearOfStudy = yearOfStudy;
        this.qualifications = qualifications;
        this.preferences = new ArrayList<>();
    }

    public Student(){}

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Qualifications getQualifications() {
        return qualifications;
    }

    public void setQualifications(Qualifications qualifications) {
        this.qualifications = qualifications;
    }

    public ArrayList<Research> getResearchMatches() {
        App.getApiService().getResearchMatches(id).enqueue(new Callback<List<Research>>() {
            @Override
            public void onResponse(Call<List<Research>> call, Response<List<Research>> response) {
                List<Research> researches = response.body();
                researchMatches.addAll(researches);
            }

            @Override
            public void onFailure(Call<List<Research>> call, Throwable t) {

            }
        });
        return researchMatches;
    }

    public ArrayList<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(ArrayList<Preference> preferences) {
        this.preferences = preferences;
    }
}
