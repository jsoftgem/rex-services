package com.jsofttechnologies.rexwar.model.management;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsofttechnologies.jpa.util.FlowJpe;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 2/25/2015 2:39.
 */
@Entity
@Table(name = "war_customer_market_school_year")
@NamedQueries({
        @NamedQuery(name = WarCustomerMarketSchoolYear.FIND_ALL, query = "select e from WarCustomerMarketSchoolYear e"),
        @NamedQuery(name = WarCustomerMarketSchoolYear.FIND_BY_SCHOOL_YEAR, query = "select wm from WarCustomerMarketSchoolYear wm where wm.schoolYear.id =:schoolYear"),
        @NamedQuery(name = WarCustomerMarketSchoolYear.FIND_BY_SCHOOL_YEAR_AGENT, query = "select wm from WarCustomerMarketSchoolYear wm where wm.schoolYear.id =:schoolYear and wm.ownerAgentId =:agent"),
        @NamedQuery(name = WarCustomerMarketSchoolYear.FIND_BY_SCHOOL_YEAR_AGENT_CUSTOMER, query = "select wm from WarCustomerMarketSchoolYear wm where wm.schoolYear.id =:schoolYear and wm.ownerAgentId =:agent and wm.warCustomer.id =:customer")

})

public class WarCustomerMarketSchoolYear implements FlowJpe {

    public static final String FIND_ALL = "WarCustomerMarketSchoolYear.FIND_ALL";
    public static final String FIND_BY_SCHOOL_YEAR = "WarCustomerMarketSchoolYear.FIND_BY_SCHOOL_YEAR";
    public static final String FIND_BY_SCHOOL_YEAR_AGENT = "WarCustomerMarketSchoolYear.FIND_BY_SCHOOL_YEAR_AGENT";
    public static final String FIND_BY_SCHOOL_YEAR_AGENT_CUSTOMER = "WarCustomerMarketSchoolYear.FIND_BY_SCHOOL_YEAR_AGENT_CUSTOMER";

    @Id
    @Column(name = "war_customer_market_school_year_id", nullable = false)
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
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private WarCustomer warCustomer;

    @Column(name = "school_year", nullable = false)
    private Long schoolYear;

    @Formula("(select sy.description from war_report_school_year sy where sy.war_report_school_year_id = school_year)")
    private String schoolYearDescription;


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

    public WarCustomer getWarCustomer() {
        return warCustomer;
    }

    public void setWarCustomer(WarCustomer warCustomer) {
        this.warCustomer = warCustomer;
    }

    public Long getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Long schoolYear) {
        this.schoolYear = schoolYear;
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

    public Long getOwnerAgentId() {
        return ownerAgentId;
    }

    public void setOwnerAgentId(Long ownerAgentId) {
        this.ownerAgentId = ownerAgentId;
    }

    public String getSchoolYearDescription() {
        return schoolYearDescription;
    }

    public void setSchoolYearDescription(String schoolYearDescription) {
        this.schoolYearDescription = schoolYearDescription;
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
