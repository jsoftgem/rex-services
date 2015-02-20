package com.jsofttechnologies.rexwar.util.contants;

/**
 * Created by Jerico on 1/21/2015.
 */
public enum Cycle {

    EVALUATION("Evaluation"), ORDERING("Ordering"), DELIVERY("Delivery"), COLLECTION("Collection");

    private String cycle;

    Cycle(String cycle) {
        this.cycle = cycle;
    }


    public String getCycle() {
        return cycle;
    }

    @Override
    public String toString() {
        return cycle;
    }
}
