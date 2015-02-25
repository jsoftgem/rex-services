package com.jsofttechnologies.jpa.util;

/**
 * Created by Jerico on 12/15/2014.
 */
public enum FlowUploadedType {

    EMBLEM("emblem"), AVATAR("avatar"),ATTACHMENT("attachment");
    private String type;

    FlowUploadedType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
