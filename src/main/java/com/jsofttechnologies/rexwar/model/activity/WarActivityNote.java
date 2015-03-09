package com.jsofttechnologies.rexwar.model.activity;

import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.rexwar.util.contants.Month;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 3/8/2015.
 */
@Entity
@Table(name = "war_activity_note")
@NamedQueries({
        @NamedQuery(name = WarActivityNote.FIND_ALL, query = "select e from WarActivityNote e"),
        @NamedQuery(name = WarActivityNote.FIND_BY_ACTIVITY_DAY, query = "select e from WarActivityNote e where e.agentId=:agentId " +
                "and e.year=:year and e.month=:month and e.day=:day")
})
public class WarActivityNote implements FlowJpe {

    public static final String FIND_ALL = "WarActivityNote.FIND_ALL";
    public static final String FIND_BY_ACTIVITY_DAY = "WarActivityNote.FIND_BY_ACTIVITY_DAY";

    @Id
    @Column(name = "war_activity_note_id", nullable = false)
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
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "war_activity_note_agent_id", nullable = false)
    private Long agentId;
    @Column(name = "war_activity_note_agent_year", nullable = false)
    private Integer year;
    @Column(name = "war_activity_note_agent_month", nullable = false)
    @Enumerated(EnumType.STRING)
    private Month month;
    @Column(name = "war_activity_note_agent_day", nullable = false)
    private Integer day;

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

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDt = new Date();
    }


    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
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
}
