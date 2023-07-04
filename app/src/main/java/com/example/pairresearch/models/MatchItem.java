package com.example.pairresearch.models;

public class MatchItem {

    private String name;
    private String researchName;
    private String institute;
    private String email;
    private String phone;
    private String university;
    private String yearOfStudy;

    public MatchItem(String name, String institute, String email, String phone, String university, String yearOfStudy, String researchName) {
        this.name = name;
        this.institute = institute;
        this.email = email;
        this.phone = phone;
        this.university = university;
        this.yearOfStudy = yearOfStudy;
        this.researchName = researchName;
    }


    public void setResearchName(String researchName) {
        this.researchName = researchName;
    }

    public String getResearchName() {
        return researchName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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


    public String getName() {
        return name;
    }

    public String getInstitute() {
        return institute;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
