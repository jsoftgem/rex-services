package com.jsofttechnologies.rexwar.model.reports;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jerico on 2/7/2015.
 */
public class WarReportWeeklyAgentViewCustomerID implements Serializable {


    private String customer;
    private Date date;
    private Long agent;


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getAgent() {
        return agent;
    }

    public void setAgent(Long agent) {
        this.agent = agent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarReportWeeklyAgentViewCustomerID that = (WarReportWeeklyAgentViewCustomerID) o;

        if (agent != null ? !agent.equals(that.agent) : that.agent != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customer != null ? customer.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (agent != null ? agent.hashCode() : 0);
        return result;
    }
}
