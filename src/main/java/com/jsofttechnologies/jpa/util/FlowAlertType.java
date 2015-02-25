package com.jsofttechnologies.jpa.util;

/**
 * Created by Jerico on 12/23/2014.
 */
public enum FlowAlertType {

    SYSTEM("system"), SILENT("silent"), MESSAGE("message"), NOTICE("notice"), BROADCAST("broadcast");

    private String alert;

    FlowAlertType(String alert) {
        this.alert = alert;
    }


    public String toString() {
        return alert;
    }

}


