package com.jsofttechnologies.rexwar.model.management;

import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 2/26/15 1902.
 */
@Entity
@Table(name = "war_customer_tag")
@NamedQueries({
        @NamedQuery(name = WarCustomerTag.FIND_ALL, query = "select e from WarCustomerTag e")
})
public class WarCustomerTag implements FlowJpe {

    public static final String FIND_ALL = "WarCustomerTag.FIND_ALL";

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
    @Column(name = "war_customer_tag_market_school_year", nullable = false)
    private Long customerMarketSchoolYearId;
    @Column(name = "war_customer_tag_market_region", nullable = false)
    private Long regionId;
    @Column(name = "war_customer_tag_region_code", nullable = false)
    private String regionCode;
    @Column(name = "war_customer_tag_index", nullable = false)
    private Integer index;

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

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDt = new Date();
    }
}
