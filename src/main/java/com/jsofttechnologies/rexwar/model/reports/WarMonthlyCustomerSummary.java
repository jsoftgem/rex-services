package com.jsofttechnologies.rexwar.model.reports;

import com.jsofttechnologies.rexwar.util.contants.Month;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by jerico on 3/14/2015.
 */
@Entity
@XmlRootElement
@IdClass(WarMonthlyCustomerSummaryID.class)
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = WarMonthlyCustomerSummary.PROCEDURE_MONTHLY_CUSTOMER_SUMMARY,
                procedureName = WarMonthlyCustomerSummary.PROCEDURE_MONTHLY_CUSTOMER_SUMMARY,
                resultClasses = {WarMonthlyCustomerSummary.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarMonthlyCustomerSummary.IN_SCHOOL_YEAR, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarMonthlyCustomerSummary.IN_AGENT, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarMonthlyCustomerSummary.IN_REGION_CODE, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarMonthlyCustomerSummary.IN_TAG, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarMonthlyCustomerSummary.IN_SIZE, type = Integer.class)
                }
        )
})
public class WarMonthlyCustomerSummary implements Serializable {

    public static final String PROCEDURE_MONTHLY_CUSTOMER_SUMMARY = "monthly_customer_summary";
    public static final String IN_SCHOOL_YEAR = "schoolYear";
    public static final String IN_AGENT = "agent";
    public static final String IN_REGION_CODE = "regionCode";
    public static final String IN_TAG = "tag";
    public static final String IN_SIZE = "size";
    @Id
    @Column(name = "MONTH")
    private String month;
    @Id
    @Column(name = "customer")
    private String customer;
    @Id
    @Column(name = "school_year")
    private Long schoolYearId;
    @Id
    @Column(name = "agent")
    private Long agentId;
    @Id
    @Column(name = "region")
    private String region;
    @Column(name = "report_customer_id")
    private Long customerId;
    @Column(name = "report_materials_advisor")
    private String materialsAdvisor;
    @Column(name = "report_month")
    @Enumerated(EnumType.STRING)
    private Month reportMonth;
    @Column(name = "report_year")
    private Integer year;
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
    @Column(name = "report_book_list")
    private Integer bookList;
    @Column(name = "report_updated_customer_info_sheet")
    private Integer ucis;
    @Column(name = "report_implemented_ex_sem")
    private Integer ies;
    @Column(name = "top")
    private Integer top;
    @Column(name = "report_frequency")
    private Integer frequency;
    @Column(name = "war_agent_initials")
    private String agentInitials;

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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getMaterialsAdvisor() {
        return materialsAdvisor;
    }

    public void setMaterialsAdvisor(String materialsAdvisor) {
        this.materialsAdvisor = materialsAdvisor;
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

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getAgentInitials() {
        return agentInitials;
    }

    public void setAgentInitials(String agentInitials) {
        this.agentInitials = agentInitials;
    }
}
