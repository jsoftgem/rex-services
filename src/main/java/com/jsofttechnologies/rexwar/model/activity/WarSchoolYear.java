package com.jsofttechnologies.rexwar.model.activity;

import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.rexwar.util.contants.Month;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jerico on 1/28/2015 00:50.
 */
@Entity
@Table(name = "war_report_school_year")
@NamedQueries({
        @NamedQuery(name = WarSchoolYear.FIND_ALL, query = "select e from WarSchoolYear e")
})
public class WarSchoolYear implements FlowJpe {

    public static final String FIND_ALL = "WarSchoolYear.FIND_ALL";

    @Id
    @Column(name = "war_report_school_year_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_dt", nullable = false)
    private Date createdDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_dt")
    private Date updatedDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_dt")
    private Date startDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_dt")
    private Date endDt;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "war_report_school_year_period_month", nullable = false)
    @Enumerated(EnumType.STRING)
    private Month periodMonth;
    @Column(name = "war_report_school_year_period_year", nullable = false)
    private int periodYear;
    @Column(name = "war_report_school_year_period_month_to", nullable = false)
    @Enumerated(EnumType.STRING)
    private Month periodMonthTo;
    @Column(name = "war_report_school_year_period_year_to", nullable = false)
    private int periodYearTo;
    @Column(name = "created_by_id", nullable = false)
    private Long createByUserId;
    @Transient
    private Date date;

    @Override
    public void setId(Object id) {
        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    @Override
    public Date getCreatedDt() {
        return createdDt;
    }

    @Override
    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    @Override
    public Date getUpdatedDt() {
        return updatedDt;
    }

    @Override
    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    @Override
    public Date getStartDt() {
        return startDt;
    }

    @Override
    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    @Override
    public Date getEndDt() {
        return endDt;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public Month getPeriodMonth() {
        return periodMonth;
    }

    public void setPeriodMonth(Month periodMonth) {
        this.periodMonth = periodMonth;
    }

    public int getPeriodYear() {
        return periodYear;
    }

    public void setPeriodYear(int periodYear) {
        this.periodYear = periodYear;
    }

    public Month getPeriodMonthTo() {
        return periodMonthTo;
    }

    public void setPeriodMonthTo(Month periodMonthTo) {
        this.periodMonthTo = periodMonthTo;
    }

    public int getPeriodYearTo() {
        return periodYearTo;
    }

    public void setPeriodYearTo(int periodYearTo) {
        this.periodYearTo = periodYearTo;
    }

    public Long getCreateByUserId() {
        return createByUserId;
    }

    public void setCreateByUserId(Long createByUserId) {
        this.createByUserId = createByUserId;
    }

    public Date getDate() {


        Month month = getPeriodMonth();
        Calendar calendar = Calendar.getInstance();
        if (month != null) {
            calendar.set(Calendar.MONTH, month.getCalendar());
        } else {
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
        }
        calendar.set(Calendar.YEAR, getPeriodYear());
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        date = calendar.getTime();

        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
    }

    @Override
    public void preUpdate() {
        updatedDt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarSchoolYear that = (WarSchoolYear) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
