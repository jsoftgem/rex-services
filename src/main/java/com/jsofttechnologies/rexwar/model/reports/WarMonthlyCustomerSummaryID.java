package com.jsofttechnologies.rexwar.model.reports;

/**
 * Created by jerico on 3/14/2015.
 */
public class WarMonthlyCustomerSummaryID {
    private String month;
    private String customer;
    private Long schoolYearId;
    private Long agentId;
    private String region;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Long getSchoolYearId() {
        return schoolYearId;
    }

    public void setSchoolYearId(Long schoolYearId) {
        this.schoolYearId = schoolYearId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarMonthlyCustomerSummaryID that = (WarMonthlyCustomerSummaryID) o;

        if (agentId != null ? !agentId.equals(that.agentId) : that.agentId != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (month != null ? !month.equals(that.month) : that.month != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (schoolYearId != null ? !schoolYearId.equals(that.schoolYearId) : that.schoolYearId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = month != null ? month.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (schoolYearId != null ? schoolYearId.hashCode() : 0);
        result = 31 * result + (agentId != null ? agentId.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        return result;
    }
}
