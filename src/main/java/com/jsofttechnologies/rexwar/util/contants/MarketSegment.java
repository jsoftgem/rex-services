package com.jsofttechnologies.rexwar.util.contants;

/**
 * Created by Jerico on 2/2/2015.
 */
public enum MarketSegment {

    HMP("High Market Potential"), MMP1("Mid-market Potential 1"), MMP2("Mid-market Potential 2"), LMP1("Low Market Potential 1"), LMP2("Low Market Potential 2");

    private String description;

    MarketSegment(String description) {
        this.description = description;
    }

}
