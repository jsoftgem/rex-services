package com.jsofttechnologies.rexwar.model.reports;

import java.io.Serializable;

/**
 * Created by Jerico on 3/11/2015.
 */
public class WarAgentCustomerSummaryID implements Serializable {


    private String month;
    private String week;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarAgentCustomerSummaryID that = (WarAgentCustomerSummaryID) o;

        if (month != null ? !month.equals(that.month) : that.month != null) return false;
        if (week != null ? !week.equals(that.week) : that.week != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = month != null ? month.hashCode() : 0;
        result = 31 * result + (week != null ? week.hashCode() : 0);
        return result;
    }
}
