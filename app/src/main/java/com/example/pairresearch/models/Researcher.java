package com.example.pairresearch.models;

import com.example.pairresearch.models.enums.MedicalTitle;

import java.util.ArrayList;
import java.util.Date;

public class Researcher extends User{

    private MedicalTitle title;
    private String institute;
    private String labDescription;
    private ArrayList<Research> researches;

    public Researcher(){}

    public Researcher(String id, String name, String email, String description, String password, String linkedin, Date registrationDate, long phoneNumber, boolean active, MedicalTitle title, String institute, String labDescription) {
        super(id, name, email, description, password, linkedin, registrationDate, phoneNumber, active);
        this.title = title;
        this.institute = institute;
        this.labDescription = labDescription;
        this.researches = new ArrayList<>();
    }

    public Researcher(MedicalTitle title, String institute, String labDescription) {
        this.title = title;
        this.institute = institute;
        this.labDescription = labDescription;
        this.researches = new ArrayList<>();
    }
}
