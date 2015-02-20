package com.jsofttechnologies.rexwar.model.activty;

import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.rexwar.util.contants.Month;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 1/31/2015 0407.
 */
@Entity
@Table(name = "war_activity_planner")
@NamedQueries({
        @NamedQuery(name = WarPlanner.FIND_ALL, query = "select e from WarPlanner e"),
        @NamedQuery(name = WarPlanner.FIND_BY_AGENT, query = "select wp from WarPlanner wp where wp.schoolYear =:schoolYear and wp.agentId =:agent and wp.month =:month and wp.year =:year"),
})
public class WarPlanner implements FlowJpe {

    public static final String FIND_ALL = "WarPlanner.FIND_ALL";
    public static final String FIND_BY_AGENT = "WarPlanner.FIND_BY_AGENT";
    @Id
    @Column(name = "war_activity_planner_id", nullable = false)
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
    @Column(name = "description")
    private String description;
    @Column(name = "war_activity_planner_school_year", nullable = false)
    private Long schoolYear;
    @Column(name = "war_activity_planner_agent", nullable = false)
    private Long agentId;
    @Column(name = "war_activity_planner_enabled")
    private Boolean enabled;
    @Column(name = "war_activity_planner_month", nullable = false)
    @Enumerated(EnumType.STRING)
    private Month month;
    @Column(name = "war_activity_planner_year", nullable = false)
    private Integer year;

    public WarPlanner() {

    }


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

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
    }

    @Override
    public void preUpdate() {
        updatedDt = new Date();
    }
}
