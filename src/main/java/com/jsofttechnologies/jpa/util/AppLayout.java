package com.jsofttechnologies.jpa.util;

/**
 * Created by Jerico on 3/3/2015.
 */
public enum AppLayout {

    FULLSCREEN("fullscreen"), FLUIDSCREEN("fluidscreen");
    private String screen;

    AppLayout(String screen) {
        this.screen = screen;
    }

    public String getScreen() {
        return screen;
    }
}
