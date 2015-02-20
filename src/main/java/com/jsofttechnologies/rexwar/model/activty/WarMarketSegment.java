package com.jsofttechnologies.rexwar.model.activty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.rexwar.util.contants.MarketSegment;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Jerico on 1/27/2015 17:21.
 */
@Entity
@Table(name = "war_report_market_segment", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"war_report_market_segment", "war_report_market_control_school_year"})
})
@NamedQueries({
        @NamedQuery(name = WarMarketSegment.FIND_ALL, query = "select e from WarMarketSegment e")
})
public class WarMarketSegment implements FlowJpe {

    public static final String FIND_ALL = "WarMarketSegment.FIND_ALL";

    @Id
    @Column(name = "war_report_market_segment_id", nullable = false)
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
    @Column(name = "war_report_market_segment", nullable = false)
    @Enumerated(EnumType.STRING)
    private MarketSegment marketSegment;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<WarMarketSegmentControl> marketSegmentControlSet;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "war_report_market_control_school_year", referencedColumnName = "war_report_school_year_id")
    private WarSchoolYear schoolYear;

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

    public MarketSegment getMarketSegment() {
        return marketSegment;
    }

    public void setMarketSegment(MarketSegment marketSegment) {
        this.marketSegment = marketSegment;
    }

    public Set<WarMarketSegmentControl> getMarketSegmentControlSet() {
        return marketSegmentControlSet;
    }

    public void setMarketSegmentControlSet(Set<WarMarketSegmentControl> marketSegmentControlSet) {
        this.marketSegmentControlSet = marketSegmentControlSet;
    }

    public WarSchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(WarSchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarMarketSegment segment = (WarMarketSegment) o;

        if (marketSegment != segment.marketSegment) return false;
        if (schoolYear != null ? !schoolYear.equals(segment.schoolYear) : segment.schoolYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = marketSegment.hashCode();
        result = 31 * result + (schoolYear != null ? schoolYear.hashCode() : 0);
        return result;
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
