package com.example.pairresearch.models;

import com.example.pairresearch.App;
import com.example.pairresearch.models.enums.Degree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Research {
    private int id;
    private int researcherId;
    private String name;
    private String description;
    private Degree forDegree;
    private String payment;
    private ArrayList<MediaUpload> mediaUploads;
    private ArrayList<Student> studentMatches;
    private boolean finalProjectSuitable;
    private long qualificationsNeeded;
    private boolean active;

    public Research(int id, int researcherId, String name, String description, Degree forDegree, String payment, boolean finalProjectSuitable, long qualificationsNeeded, boolean active) {
        this.id = id;
        this.researcherId = researcherId;
        this.name = name;
        this.description = description;
        this.forDegree = forDegree;
        this.payment = payment;
        this.mediaUploads = new ArrayList<>();
        this.studentMatches = new ArrayList<>();
        this.finalProjectSuitable = finalProjectSuitable;
        this.qualificationsNeeded = qualificationsNeeded;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResearcherId() {
        return researcherId;
    }

    public void setResearcherId(int researcherId) {
        this.researcherId = researcherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Degree getForDegree() {
        return forDegree;
    }

    public void setForDegree(Degree forDegree) {
        this.forDegree = forDegree;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public ArrayList<MediaUpload> getMediaUploads() {
        return mediaUploads;
    }

    public boolean addMediaUpload(MediaUpload mediaUpload) {
        return mediaUploads.add(mediaUpload);
    }

    public boolean removeMediaUpload(MediaUpload mediaUpload) {
        return mediaUploads.remove(mediaUpload);
    }

    public ArrayList<Student> getStudentMatches() {
        App.getApiService().getStudentMatches(id).enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students = response.body();
                studentMatches.addAll(students);
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {

            }
        });
        return studentMatches;
    }

    public ArrayList<String> getAllMediaSources(){
        ArrayList<String> retVal = new ArrayList<>();
        for(MediaUpload media : mediaUploads){
            retVal.add(media.getMediaSource());
        }
        return retVal;
    }

    public boolean isFinalProjectSuitable() {
        return finalProjectSuitable;
    }

    public void setFinalProjectSuitable(boolean finalProjectSuitable) {
        this.finalProjectSuitable = finalProjectSuitable;
    }

    public long getQualificationsNeeded() {
        return qualificationsNeeded;
    }

    public void setQualificationsNeeded(long qualificationsNeeded) {
        this.qualificationsNeeded = qualificationsNeeded;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Research)) return false;
        Research research = (Research) o;
        return id == research.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
