package com.example.pairresearch.models;

import com.example.pairresearch.models.enums.MedicalTitle;

import java.util.ArrayList;
import java.util.Date;

public class Researcher extends User{

    private MedicalTitle title;
    private String institute;
    private String labDescription;
    private ArrayList<Research> researches;
    private Research activeResearch;

    public Researcher(){}

    public Researcher(int id, String name, String email, String description, String password, String linkedin, Date registrationDate, String phoneNumber, boolean active, MedicalTitle title, String institute, String labDescription) {
        super(id, name, email, description, password, linkedin, registrationDate, phoneNumber, active);
        this.title = title;
        this.institute = institute;
        this.labDescription = labDescription;
        this.researches = new ArrayList<>();
        this.activeResearch = null;
    }

    public Researcher(MedicalTitle title, String institute, String labDescription) {
        this.title = title;
        this.institute = institute;
        this.labDescription = labDescription;
        this.researches = new ArrayList<>();
        this.activeResearch = null;

    }

    public MedicalTitle getTitle() {
        return title;
    }

    public void setTitle(MedicalTitle title) {
        this.title = title;
    }

    public String getInstitute() {
        return institute;
    }

    public Research getActiveResearch() {
        return activeResearch;
    }

    public void setActiveResearch(Research activeResearch) {
        this.activeResearch = activeResearch;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getLabDescription() {
        return labDescription;
    }

    public void setLabDescription(String labDescription) {
        this.labDescription = labDescription;
    }

    public ArrayList<Research> getResearches() {
        return researches;
    }
    public ArrayList<String> getResearchesNames() {
        ArrayList<String> retVal = new ArrayList<String>();
        for (Research research : researches) {
            retVal.add(research.getName());
        }
        return retVal;
    }

    public void setResearches(ArrayList<Research> researches) {
        this.researches = researches;
    }
}
