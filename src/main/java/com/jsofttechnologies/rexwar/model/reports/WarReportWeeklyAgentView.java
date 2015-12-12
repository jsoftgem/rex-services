package com.jsofttechnologies.rexwar.model.reports;

import com.jsofttechnologies.report.utlil.ReportColumn;
import com.jsofttechnologies.report.utlil.ReportHeader;
import com.jsofttechnologies.rexwar.util.contants.Month;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jerico on 2/6/2015.
 */
@Entity
@IdClass(WarReportWeeklyAgentViewID.class)
@XmlRootElement
@ReportHeader(name = "Weekly Activity Report")
public class WarReportWeeklyAgentView implements Serializable {
    @Id
    @Column(name = "report_planner_id", nullable = false)
    private Long reportPlannerId;
    @Id
    @Column(name = "report_agent_id", nullable = false)
    private Long agentId;
    @ReportColumn(name = "Materials Advisor")
    @Column(name = "report_materials_advisor", nullable = false)
    private String materialsAdvisor;
    @Column(name = "report_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date reportDate;
    @ReportColumn(name = "Month")
    @Id
    @Column(name = "report_month", nullable = false)
    @Enumerated(EnumType.STRING)
    private Month reportMonth;
    @ReportColumn(name = "Year")
    @Column(name = "report_year", nullable = false)
    private Integer year;
    @Column(name = "report_region", nullable = false)
    private String region;
    @ReportColumn(name = "Week")
    @Id
    @Column(name = "report_week", nullable = false)
    private Integer week;
    @Column(name = "report_planned_target")
    private Integer plannedTarget;
    @Column(name = "report_unplanned_target")
    private Integer unplannedTarget;
    @ReportColumn(name = "Planned")
    @Column(name = "report_planned_actual")
    private Integer plannedActual;
    @ReportColumn(name = "Unplanned")
    @Column(name = "report_unplanned_actual")
    private Integer unplannedActual;
    @ReportColumn(name = "Target")
    @Column(name = "report_total_activity")
    private Integer totalActivity;
    @Column(name = "report_total_actual")
    private Integer totalActual;
    @Column(name = "report_planned_call_productivity")
    private Double plannedCallProductivity;
    @Column(name = "report_unplanned_call_productivity")
    private Double unplannedCallProductivity;
    @ReportColumn(name = "Call Productivity", converter = TotalProductivityReportConverter.class)
    @Column(name = "report_total_call_productivity")
    private Double totalCallProductivity;
    @ReportColumn(name = "Exam copies distribution")
    @Column(name = "report_exam_copies_distribution")
    private Integer ecd;
    @ReportColumn(name = "Invitation to events")
    @Column(name = "report_invitation_to_events")
    private Integer ite;
    @ReportColumn(name = "Confirmation of events")
    @Column(name = "report_confirmation_of_events")
    private Integer coe;
    @ReportColumn(name = "Follow up payment")
    @Column(name = "report_follow_up_payment")
    private Integer fp;
    @ReportColumn(name = "Giveaways distribution")
    @Column(name = "report_giveaways_distribution")
    private Integer gd;
    @ReportColumn(name = "Delivery of incentive/donation")
    @Column(name = "report_delivery_of_incentive_donation")
    private Integer doi;
    @ReportColumn(name = "Purchase order")
    @Column(name = "report_purchase_order")
    private Integer po;
    @ReportColumn(name = "Delivery of additional order")
    @Column(name = "report_delivery_of_additional_order_trm_compliance")
    private Integer daotrc;
    @ReportColumn(name = "Booklist")
    @Column(name = "report_book_list")
    private Integer bookList;
    @ReportColumn(name = "Customer info sheet")
    @Column(name = "report_updated_customer_info_sheet")
    private Integer ucis;
    @ReportColumn(name = "Implemented Ex sem")
    @Column(name = "report_implemented_ex_sem")
    private Integer ies;
    @Column(name = "report_worked_with")
    private Integer workedWith;

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

    public String getMaterialsAdvisor() {
        return materialsAdvisor;
    }

    public void setMaterialsAdvisor(String materialsAdvisor) {
        this.materialsAdvisor = materialsAdvisor;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Month getReportMonth() {
        return reportMonth;
    }

    public void setReportMonth(Month reportMonth) {
        this.reportMonth = reportMonth;
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

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getPlannedTarget() {
        return plannedTarget;
    }

    public void setPlannedTarget(Integer plannedTarget) {
        this.plannedTarget = plannedTarget;
    }

    public Integer getUnplannedTarget() {
        return unplannedTarget;
    }

    public void setUnplannedTarget(Integer unplannedTarget) {
        this.unplannedTarget = unplannedTarget;
    }

    public Integer getPlannedActual() {
        return plannedActual;
    }

    public void setPlannedActual(Integer plannedActual) {
        this.plannedActual = plannedActual;
    }

    public Integer getUnplannedActual() {
        return unplannedActual;
    }

    public void setUnplannedActual(Integer unplannedActual) {
        this.unplannedActual = unplannedActual;
    }

    public Integer getTotalActivity() {
        return totalActivity;
    }

    public void setTotalActivity(Integer totalActivity) {
        this.totalActivity = totalActivity;
    }

    public Integer getTotalActual() {
        return totalActual;
    }

    public void setTotalActual(Integer totalActual) {
        this.totalActual = totalActual;
    }

    public Double getPlannedCallProductivity() {
        return plannedCallProductivity;
    }

    public void setPlannedCallProductivity(Double plannedCallProductivity) {
        this.plannedCallProductivity = plannedCallProductivity;
    }

    public Double getUnplannedCallProductivity() {
        return unplannedCallProductivity;
    }

    public void setUnplannedCallProductivity(Double unplannedCallProductivity) {
        this.unplannedCallProductivity = unplannedCallProductivity;
    }

    public Double getTotalCallProductivity() {
        return totalCallProductivity;
    }

    public void setTotalCallProductivity(Double totalCallProductivity) {
        this.totalCallProductivity = totalCallProductivity;
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

    public Integer getIes() {
        return ies;
    }

    public void setIes(Integer ies) {
        this.ies = ies;
    }

    public Integer getWorkedWith() {
        return workedWith;
    }

    public void setWorkedWith(Integer workedWith) {
        this.workedWith = workedWith;
    }
}
