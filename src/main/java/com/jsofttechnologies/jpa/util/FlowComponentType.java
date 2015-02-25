package com.jsofttechnologies.jpa.util;

/**
 * Created by Jerico on 11/11/2014.
 */
public enum FlowComponentType {
    TASK("task"), MODULE("module"), PAGE("page");


    private String type;

    FlowComponentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
