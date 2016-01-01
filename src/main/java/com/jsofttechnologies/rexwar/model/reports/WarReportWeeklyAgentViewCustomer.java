package com.jsofttechnologies.rexwar.model.reports;

import com.jsofttechnologies.rexwar.util.contants.Month;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jerico on 2/6/2015.
 */
@Entity
@IdClass(WarReportWeeklyAgentViewCustomerID.class)
public class WarReportWeeklyAgentViewCustomer implements Serializable {

    @Column(name = "report_week")
    private Integer week;
    @Column(name = "report_day")
    private Integer day;
    @Column(name = "report_month")
    @Enumerated(EnumType.STRING)
    private Month month;
    @Column(name = "report_year")
    private Integer year;
    @Column(name = "report_region")
    private String region;
    @Column(name = "report_planned")
    private Boolean planned;
    @Id
    @Column(name = "report_customer")
    private String customer;
    @Column(name = "report_market_potential_segment")
    private String marketSegment;
    @Id
    @Column(name = "report_agent")
    private Long agent;
    @Column(name = "report_actual")
    private Integer actual;
    @Column(name = "report_exam_copies_distribution")
    private Integer ecd;
    @Column(name = "report_invitation_to_events")
    private Integer ite;
    @Column(name = "report_confirmation_of_events")
    private Integer coe;
    @Column(name = "report_follow_up_payment")
    private Integer fp;
    @Column(name = "report_giveaways_distribution")
    private Integer gd;
    @Column(name = "report_delivery_of_incentive_donation")
    private Integer doi;
    @Column(name = "report_purchase_order")
    private Integer po;
    @Column(name = "report_delivery_of_additional_order_trm_compliance")
    private Integer daotrc;
    @Column(name = "report_booklist")
    private Integer bookList;
    @Column(name = "report_updated_customer_info_sheet")
    private Integer ucis;
    @Column(name = "report_implemented_ex_sem")
    private Integer ies;
    @Id
    @Column(name = "report_date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "report_worked_with")
    private Integer workedWith;
    @Column(name = "report_reason")
    private String reasonForNonCoverage;
    @Column(name = "report_activity_id")
    private Long reportActivityId;


    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getIes() {
        return ies;
    }

    public void setIes(Integer ies) {
        this.ies = ies;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Boolean getPlanned() {
        return planned;
    }

    public void setPlanned(Boolean planned) {
        this.planned = planned;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getMarketSegment() {
        return marketSegment;
    }

    public void setMarketSegment(String marketSegment) {
        this.marketSegment = marketSegment;
    }

    public Long getAgent() {
        return agent;
    }

    public void setAgent(Long agent) {
        this.agent = agent;
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(Integer actual) {
        this.actual = actual;
    }

    public Integer getEcd() {
        return ecd;
    }

    public void setEcd(Integer ecd) {
        this.ecd = ecd;
    }

    public Integer getIte() {
        return ite;
    }

    public void setIte(Integer ite) {
        this.ite = ite;
    }

    public Integer getCoe() {
        return coe;
    }

    public void setCoe(Integer coe) {
        this.coe = coe;
    }

    public Integer getFp() {
        return fp;
    }

    public void setFp(Integer fp) {
        this.fp = fp;
    }

    public Integer getGd() {
        return gd;
    }

    public void setGd(Integer gd) {
        this.gd = gd;
    }

    public Integer getDoi() {
        return doi;
    }

    public void setDoi(Integer doi) {
        this.doi = doi;
    }

    public Integer getPo() {
        return po;
    }

    public void setPo(Integer po) {
        this.po = po;
    }

    public Integer getDaotrc() {
        return daotrc;
    }

    public void setDaotrc(Integer daotrc) {
        this.daotrc = daotrc;
    }

    public Integer getBookList() {
        return bookList;
    }

    public void setBookList(Integer bookList) {
        this.bookList = bookList;
    }

    public Integer getUcis() {
        return ucis;
    }

    public void setUcis(Integer ucis) {
        this.ucis = ucis;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getWorkedWith() {
        return workedWith;
    }

    public void setWorkedWith(Integer workedWith) {
        this.workedWith = workedWith;
    }

    public String getReasonForNonCoverage() {
        return reasonForNonCoverage;
    }

    public void setReasonForNonCoverage(String reasonForNonCoverage) {
        this.reasonForNonCoverage = reasonForNonCoverage;
    }

    public Long getReportActivityId() {
        return reportActivityId;
    }

    public void setReportActivityId(Long reportActivityId) {
        this.reportActivityId = reportActivityId;
    }
}
