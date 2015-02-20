package com.jsofttechnologies.rexwar.model.reports;

import com.jsofttechnologies.rexwar.util.contants.Month;

import java.io.Serializable;

/**
 * Created by Jerico on 2/6/2015.
 */
public class WarReportWeeklyAgentViewID implements Serializable {

    private Long reportPlannerId;
    private Long agentId;
    private Month reportMonth;
    private Integer week;

    public Long getReportPlannerId() {
        return reportPlannerId;
    }

    public void setReportPlannerId(Long reportPlannerId) {
        this.reportPlannerId = reportPlannerId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Month getReportMonth() {
        return reportMonth;
    }

    public void setReportMonth(Month reportMonth) {
        this.reportMonth = reportMonth;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarReportWeeklyAgentViewID that = (WarReportWeeklyAgentViewID) o;

        if (agentId != null ? !agentId.equals(that.agentId) : that.agentId != null) return false;
        if (reportMonth != that.reportMonth) return false;
        if (reportPlannerId != null ? !reportPlannerId.equals(that.reportPlannerId) : that.reportPlannerId != null)
            return false;
        if (week != null ? !week.equals(that.week) : that.week != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reportPlannerId != null ? reportPlannerId.hashCode() : 0;
        result = 31 * result + (agentId != null ? agentId.hashCode() : 0);
        result = 31 * result + (reportMonth != null ? reportMonth.hashCode() : 0);
        result = 31 * result + (week != null ? week.hashCode() : 0);
        return result;
    }
}
