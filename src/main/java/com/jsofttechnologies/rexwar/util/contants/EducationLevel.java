package com.jsofttechnologies.rexwar.util.contants;

/**
 * Created by Jerico on 1/20/2015.
 */
public enum EducationLevel {

    PreELEMENTARY("Pre-Elementary"), ELEMENTARY("Elementary"),
    SECONDARY("Secondary"), TERTIARY("Tertiary");

    private String level;

    EducationLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return level;
    }
}
