package com.jsofttechnologies.rexwar.model.management;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jsofttechnologies.jpa.util.FlowJpe;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 2/26/15 1902.
 */
@Entity
@Table(name = "war_customer_tag")
@NamedQueries({
        @NamedQuery(name = WarCustomerTag.FIND_ALL, query = "select e from WarCustomerTag e"),
        @NamedQuery(name = WarCustomerTag.FIND_BY_AGENT, query = "select e from WarCustomerTag e where e.agentId=:agentId order by e.index asc"),
        @NamedQuery(name = WarCustomerTag.FIND_IF_TOP_20, query = "select e from WarCustomerTag e where e.agentId=:agentId and e.customerId=:customerId and e.index <= 20 order by e.index asc")
})
public class WarCustomerTag implements FlowJpe {

    public static final String FIND_ALL = "WarCustomerTag.FIND_ALL";
    public static final String FIND_BY_AGENT = "WarCustomerTag.FIND_BY_ASSIGNED_AGENT";
    public static final String FIND_IF_TOP_20 = "WarCustomerTag.FIND_IF_TOP_20";

    @Id
    @Column(name = "war_customer_tag_id", nullable = false)
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
    @Column(name = "war_customer_tag_agent", nullable = false)
    private Long agentId;
    @Column(name = "war_customer_tag_customer", nullable = false)
    private Long customerId;
    @Formula("(select wy.war_customer_market_school_year_id from war_customer_market_school_year wy " +
            "inner join war_customer_war_customer_market_school_year wsy on " +
            "wsy.warCustomerMarketSchoolYears_war_customer_market_school_year_id = wy.war_customer_market_school_year_id " +
            "inner join war_report_school_year wrsy on wrsy.war_report_school_year_id = wy.school_year " +
            "where wsy.war_customer_customer_id = war_customer_tag_customer order by wrsy.war_report_school_year_period_year desc limit 1)")
    private Long customerMarketSchoolYearId;
    @Column(name = "war_customer_tag_market_region", nullable = false)
    private Long regionId;
    @Column(name = "war_customer_tag_region_code", nullable = false)
    private String regionCode;
    @Column(name = "war_customer_tag_index", nullable = false)
    private Integer index;
    @Formula("(select sh.school_name from war_customer_school sh inner join war_customer wc on wc.war_customer_school = sh.war_customer_school_id where wc.customer_id = war_customer_tag_customer)")
    private String customerName;
    @Formula("(select concat(wy.market_potential_segment,' - ', wy.market_potential) from war_customer_market_school_year wy " +
            "inner join war_customer_war_customer_market_school_year wsy on " +
            "wsy.warCustomerMarketSchoolYears_war_customer_market_school_year_id = wy.war_customer_market_school_year_id " +
            "inner join war_report_school_year wrsy on wrsy.war_report_school_year_id = wy.school_year " +
            "where wsy.war_customer_customer_id = war_customer_tag_customer order by wrsy.war_report_school_year_period_year desc limit 1)")
    private String marketDescription;
    @Transient
    @JsonProperty
    private Boolean toBeUpdated;

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

    public Long getCustomerMarketSchoolYearId() {
        return customerMarketSchoolYearId;
    }

    public void setCustomerMarketSchoolYearId(Long customerMarketSchoolYearId) {
        this.customerMarketSchoolYearId = customerMarketSchoolYearId;
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMarketDescription() {
        return marketDescription;
    }

    public void setMarketDescription(String marketDescription) {
        this.marketDescription = marketDescription;
    }

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarCustomerTag that = (WarCustomerTag) o;

        if (agentId != null ? !agentId.equals(that.agentId) : that.agentId != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (index != null ? !index.equals(that.index) : that.index != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = agentId != null ? agentId.hashCode() : 0;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (index != null ? index.hashCode() : 0);
        return result;
    }

    public Boolean getToBeUpdated() {
        return toBeUpdated;
    }

    public void setToBeUpdated(Boolean toBeUpdated) {
        this.toBeUpdated = toBeUpdated;
    }
}
