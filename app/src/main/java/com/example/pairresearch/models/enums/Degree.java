package com.example.pairresearch.models.enums;

public enum Degree {
    BACHELORS("Bachelor's"),
    MASTERS("Master's"),
    PHD("Ph.D.");

    private final String displayName;

    Degree(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
