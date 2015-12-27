package com.jsofttechnologies.rexwar.model.activity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.rexwar.util.contants.ActivityType;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 1/30/2015 05:02.
 */
@Entity
@Table(name = "war_activity")
@NamedQueries({
        @NamedQuery(name = WarActivity.FIND_ALL, query = "select a from WarActivity a"),
        @NamedQuery(name = WarActivity.FIND_BY_SCHOOL_YEAR, query = "select a from WarActivity a where a.schoolYear =:schoolYear and a.deleted = false"),
        @NamedQuery(name = WarActivity.FIND_BY_EVENT_SCHOOL_YEAR, query = "select a from WarActivity a where a.schoolYear =:schoolYear and a.deleted = false and a.startDt between :start and :end"),
        @NamedQuery(name = WarActivity.FIND_BY_EVENT_SCHOOL_YEAR_DATE, query = "select a from WarActivity a where a.schoolYear =:schoolYear and a.deleted = false and a.startDt =:date"),
        @NamedQuery(name = WarActivity.FIND_BY_EVENT_SCHOOL_YEAR_AGENT, query = "select a from WarActivity a where a.agentId = :agent and a.schoolYear =:schoolYear and a.deleted = false and a.startDt between :start and :end"),
        @NamedQuery(name = WarActivity.FIND_BY_EVENT_SCHOOL_YEAR_AGENT_DATE, query = "select a from WarActivity a where a.agentId = :agent and a.schoolYear =:schoolYear and a.deleted = false and a.startDt =:date")
})
public class WarActivity implements FlowJpe {

    public static final String FIND_ALL = "WarActivity.FIND_ALL";
    public static final String FIND_BY_SCHOOL_YEAR = "WarActivity.FIND_BY_SCHOOL_YEAR";
    public static final String FIND_BY_EVENT_SCHOOL_YEAR = "WarActivity.FIND_BY_EVENT_SCHOOL_YEAR";
    public static final String FIND_BY_EVENT_SCHOOL_YEAR_DATE = "WarActivity.FIND_BY_EVENT_SCHOOL_YEAR_DATE";
    public static final String FIND_BY_EVENT_SCHOOL_YEAR_AGENT = "WarActivity.FIND_BY_EVENT_SCHOOL_YEAR_AGENT";
    public static final String FIND_BY_EVENT_SCHOOL_YEAR_AGENT_DATE = "WarActivity.FIND_BY_EVENT_SCHOOL_YEAR_AGENT_DATE";
    @Id
    @Column(name = "war_activity_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_dt", nullable = false)
    private Date createdDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_dt")
    private Date updatedDt;
    @Temporal(TemporalType.DATE)
    @Column(name = "start_dt")
    private Date startDt;
    @Temporal(TemporalType.DATE)
    @Column(name = "end_dt")
    private Date endDt;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "war_activity_school_year", nullable = false)
    private Long schoolYear;
    @Column(name = "war_activity_customer_market_id", nullable = false)
    private Long customerMarketId;
    @Column(name = "war_activity_actual", length = ColumnLengths.FLAG)
    private Boolean actual;
    @Column(name = "war_activity_exam_copies_distribution", length = ColumnLengths.FLAG)
    private Boolean ecd;
    @Column(name = "war_activity_invitation_to_events", length = ColumnLengths.FLAG)
    private Boolean ite;
    @Column(name = "war_activity_confirmation_of_events", length = ColumnLengths.FLAG)
    private Boolean coe;
    @Column(name = "war_activity_follow_up_payment", length = ColumnLengths.FLAG)
    private Boolean fp;
    @Column(name = "war_activity_giveaways_distribution", length = ColumnLengths.FLAG)
    private Boolean gd;
    @Column(name = "war_activity_delivery_of_incentive_donation", length = ColumnLengths.FLAG)
    private Boolean doi;
    @Column(name = "war_activity_purchase_order", length = ColumnLengths.FLAG)
    private Boolean po;
    @Column(name = "war_activity_delivery_of_additional_order_trm_compliance", length = ColumnLengths.FLAG)
    private Boolean daotrc;
    @Column(name = "war_activity_book_list", length = ColumnLengths.FLAG)
    private Boolean bookList;
    @Column(name = "war_activity_updated_customer_info_sheet", length = ColumnLengths.FLAG)
    private Boolean ucis;
    @Column(name = "war_activity_implemented_ex_sem", length = ColumnLengths.FLAG)
    private Boolean ies;
    @Column(name = "war_activity_customer_specific_activity", columnDefinition = "TEXT")
    private String customerSpecificActivity;
    @Column(name = "war_activity_reason_for_non_coverage", columnDefinition = "TEXT")
    private String reasonForNonCoverage;
    @Formula("(select a.war_agent_initials from war_agent a where a.war_agent_id = war_activity_agent_id)")
    private String materialAdviser;
    @Column(name = "war_activity_editable", length = ColumnLengths.FLAG)
    private Boolean editable;
    @Formula("(select wa.description from war_report_school_year wa where wa.war_report_school_year_id = war_activity_school_year)")
    private String schoolYearDescription;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "war_planner", referencedColumnName = "war_activity_planner_id", nullable = false, updatable = false),
            @JoinColumn(name = "war_planner_month", referencedColumnName = "war_activity_planner_month", nullable = false, updatable = false)
    })
    private WarPlanner warPlanner;
    @Column(name = "war_activity_planned", nullable = false, length = ColumnLengths.FLAG)
    private Boolean planned;
    @Column(name = "war_activity_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityType type;
    @Column(name = "war_activity_agent_id", nullable = false)
    private Long agentId;
    @Column(name = "war_activity_week", nullable = false)
    private Integer week;
    @Column(name = "war_activity_region_id", nullable = false)
    private Long regionId;
    @Column(name = "war_activity_region_code", nullable = false)
    private String regionCode;
    @Column(name = "war_activity_worked_with")
    private Boolean workedWith;
    @Column(name = "war_activity_deleted")
    private Boolean deleted;

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

    public Long getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Long schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Long getCustomerMarketId() {
        return customerMarketId;
    }

    public void setCustomerMarketId(Long customerMarketId) {
        this.customerMarketId = customerMarketId;
    }

    public Boolean getActual() {
        return actual;
    }

    public void setActual(Boolean actual) {
        this.actual = actual;
    }

    public Boolean getEcd() {
        return ecd;
    }

    public void setEcd(Boolean ecd) {
        this.ecd = ecd;
    }

    public Boolean getIte() {
        return ite;
    }

    public void setIte(Boolean ite) {
        this.ite = ite;
    }

    public Boolean getCoe() {
        return coe;
    }

    public void setCoe(Boolean coe) {
        this.coe = coe;
    }

    public Boolean getFp() {
        return fp;
    }

    public void setFp(Boolean fp) {
        this.fp = fp;
    }

    public Boolean getGd() {
        return gd;
    }

    public void setGd(Boolean gd) {
        this.gd = gd;
    }

    public Boolean getDoi() {
        return doi;
    }

    public void setDoi(Boolean doi) {
        this.doi = doi;
    }

    public Boolean getPo() {
        return po;
    }

    public void setPo(Boolean po) {
        this.po = po;
    }

    public Boolean getDaotrc() {
        return daotrc;
    }

    public void setDaotrc(Boolean daotrc) {
        this.daotrc = daotrc;
    }

    public Boolean getBookList() {
        return bookList;
    }

    public void setBookList(Boolean bookList) {
        this.bookList = bookList;
    }

    public Boolean getUcis() {
        return ucis;
    }

    public void setUcis(Boolean ucis) {
        this.ucis = ucis;
    }

    public Boolean getIes() {
        return ies;
    }

    public void setIes(Boolean ies) {
        this.ies = ies;
    }

    public String getCustomerSpecificActivity() {
        return customerSpecificActivity;
    }

    public void setCustomerSpecificActivity(String customerSpecificActivity) {
        this.customerSpecificActivity = customerSpecificActivity;
    }

    public String getReasonForNonCoverage() {
        return reasonForNonCoverage;
    }

    public void setReasonForNonCoverage(String reasonForNonCoverage) {
        this.reasonForNonCoverage = reasonForNonCoverage;
    }

    public String getMaterialAdviser() {
        return materialAdviser;
    }

    public void setMaterialAdviser(String materialAdviser) {
        this.materialAdviser = materialAdviser;
    }

    public String getSchoolYearDescription() {
        return schoolYearDescription;
    }

    public void setSchoolYearDescription(String schoolYearDescription) {
        this.schoolYearDescription = schoolYearDescription;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public WarPlanner getWarPlanner() {
        return warPlanner;
    }

    public void setWarPlanner(WarPlanner warPlanner) {
        this.warPlanner = warPlanner;
    }

    public Boolean getPlanned() {
        return planned;
    }

    public void setPlanned(Boolean planned) {
        this.planned = planned;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Boolean getWorkedWith() {
        return workedWith;
    }

    public void setWorkedWith(Boolean workedWith) {
        this.workedWith = workedWith;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
        editable = Boolean.FALSE;
        if (deleted == null) {
            deleted = Boolean.FALSE;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedDt = new Date();
    }
}
