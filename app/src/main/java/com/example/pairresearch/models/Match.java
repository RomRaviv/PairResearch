package com.example.pairresearch.models;

public class Match {
    public int id;
    public int student_id;
    public int research_id;
    public String student_response;
    public String research_response;

    public Match() {
    }

    public Match(int id, int student_id, int research_id, String student_response, String research_response) {
        this.id = id;
        this.student_id = student_id;
        this.research_id = research_id;
        this.student_response = student_response;
        this.research_response = research_response;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getResearch_id() {
        return research_id;
    }

    public void setResearch_id(int research_id) {
        this.research_id = research_id;
    }

    public String getStudent_response() {
        return student_response;
    }

    public void setStudent_response(String student_response) {
        this.student_response = student_response;
    }

    public String getResearch_response() {
        return research_response;
    }

    public void setResearch_response(String research_response) {
        this.research_response = research_response;
    }
}
