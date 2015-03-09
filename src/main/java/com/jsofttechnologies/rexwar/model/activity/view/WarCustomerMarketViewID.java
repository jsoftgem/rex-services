package com.jsofttechnologies.rexwar.model.activity.view;

import java.io.Serializable;

/**
 * Created by Jerico on 2/20/2015.
 */
public class WarCustomerMarketViewID implements Serializable {

    private Long id;
    private Long schoolYear;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Long schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarCustomerMarketViewID that = (WarCustomerMarketViewID) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (schoolYear != null ? !schoolYear.equals(that.schoolYear) : that.schoolYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (schoolYear != null ? schoolYear.hashCode() : 0);
        return result;
    }
}
