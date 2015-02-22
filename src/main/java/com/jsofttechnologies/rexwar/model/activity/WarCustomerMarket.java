package com.jsofttechnologies.rexwar.model.activity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsofttechnologies.jpa.util.FlowJpe;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 1/27/2015 15:00.
 */
@Entity
@Table(name = "war_report_customer_market")
@NamedQueries({
        @NamedQuery(name = WarCustomerMarket.FIND_ALL, query = "select e from WarCustomerMarket e"),
        @NamedQuery(name = WarCustomerMarket.FIND_BY_SCHOOL_YEAR, query = "select wm from WarCustomerMarket wm where wm.schoolYear.id =:schoolYear"),
        @NamedQuery(name = WarCustomerMarket.FIND_BY_SCHOOL_YEAR_AGENT, query = "select wm from WarCustomerMarket wm where wm.schoolYear.id =:schoolYear and wm.ownerAgentId =:agent"),
        @NamedQuery(name = WarCustomerMarket.FIND_BY_SCHOOL_YEAR_AGENT_CUSTOMER, query = "select wm from WarCustomerMarket wm where wm.schoolYear.id =:schoolYear and wm.ownerAgentId =:agent and wm.customerId =:customer")
})
public class WarCustomerMarket implements FlowJpe {

    public static final String FIND_ALL = "WarCustomerMarket.FIND_ALL";
    public static final String FIND_BY_SCHOOL_YEAR = "WarCustomerMarket.FIND_BY_SCHOOL_YEAR";
    public static final String FIND_BY_SCHOOL_YEAR_AGENT = "WarCustomerMarket.FIND_BY_SCHOOL_YEAR_AGENT";
    public static final String FIND_BY_SCHOOL_YEAR_AGENT_CUSTOMER = "WarCustomerMarket.FIND_BY_SCHOOL_YEAR_AGENT_CUSTOMER";
    @Id
    @Column(name = "war_report_customer_market_id", nullable = false)
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
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    @Column(name = "market_potential_segment", nullable = false)
    private String marketPotentialSegment;
    @Column(name = "market_potential", nullable = false)
    private Integer marketPotential;
    @Column(name = "frequency")
    private Integer frequency;
    @Formula("(select customer.customer_code from war_customer customer where customer.customer_id = customer_id)")
    private String customerCode;
    @Formula("(select s.school_name from war_customer c join war_customer_school s on s.war_customer_school_id = c.war_customer_school where c.customer_id = customer_id)")
    private String customerName;
    @Formula("(select customer.customer_owner_agent_id from war_customer customer where customer.customer_id = customer_id)")
    private Long ownerAgentId;


    @JsonIgnore
    @ManyToOne(targetEntity = WarSchoolYear.class)
    @JoinColumn(name = "school_year", referencedColumnName = "war_report_school_year_id")
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getMarketPotentialSegment() {
        return marketPotentialSegment;
    }

    public void setMarketPotentialSegment(String marketPotentialSegment) {
        this.marketPotentialSegment = marketPotentialSegment;
    }

    public Integer getMarketPotential() {
        return marketPotential;
    }

    public void setMarketPotential(Integer marketPotential) {
        this.marketPotential = marketPotential;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public WarSchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(WarSchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Long getOwnerAgentId() {
        return ownerAgentId;
    }

    public void setOwnerAgentId(Long ownerAgentId) {
        this.ownerAgentId = ownerAgentId;
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
