package com.jsofttechnologies.rexwar.model.reports;

import com.jsofttechnologies.rexwar.util.contants.Month;

import java.io.Serializable;

/**
 * Created by Jerico on 3/10/2015.
 */
public class WarReportMonthlyCustomerViewID implements Serializable {


    private Long customerId;
    private Month month;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}
