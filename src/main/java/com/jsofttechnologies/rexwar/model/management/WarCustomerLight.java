package com.jsofttechnologies.rexwar.model.management;

import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.rexwar.model.tables.School;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Jerico on 1/27/2015 15:22.
 */
@Entity
@Table(name = "war_customer")
@NamedQueries({
        @NamedQuery(name = WarCustomerLight.FIND_ALL, query = "select e from WarCustomerLight e"),
        @NamedQuery(name = WarCustomerLight.FIND_BY_ASSIGNED_AGENT, query = "select e from WarCustomerLight e where e.ownerAgentId=:agentId")
})
public class WarCustomerLight implements FlowJpe {

    public static final String FIND_ALL = "WarCustomerLight.FIND_ALL";
    public static final String FIND_BY_ASSIGNED_AGENT = "WarCustomerLight.FIND_BY_ASSIGNED_AGENT";
    @Id
    @Column(name = "customer_id", nullable = false)
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
    @Column(name = "customer_code", unique = true, nullable = false)
    private String customerCode;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "war_customer_school", referencedColumnName = "war_customer_school_id")
    private School school;
    @Column(name = "customer_owner_agent_id")
    private Long ownerAgentId;
    @Formula("(select d.full_name from war_agent agent " +
            "join flow_user u " +
            "on u.flow_user_id = agent.user " +
            "join flow_user_detail d " +
            "on d.flow_user_detail_id = u.flow_user_detail_id " +
            "where agent.war_agent_id = customer_owner_agent_id)")
    private String ownerName;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<WarCustomerMarketSchoolYear> warCustomerMarketSchoolYears;
    @Formula("(select concat(wy.market_potential_segment,' - ', wy.market_potential) from war_customer_market_school_year wy " +
            "inner join war_customer_war_customer_market_school_year wsy on " +
            "wsy.warCustomerMarketSchoolYears_war_customer_market_school_year_id = wy.war_customer_market_school_year_id " +
            "inner join war_report_school_year wrsy on wrsy.war_report_school_year_id = wy.school_year " +
            "where wsy.war_customer_customer_id = customer_id order by wrsy.war_report_school_year_period_year desc limit 1)")
    private String marketDetail;

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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Long getOwnerAgentId() {
        return ownerAgentId;
    }

    public void setOwnerAgentId(Long ownerAgentId) {
        this.ownerAgentId = ownerAgentId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Set<WarCustomerMarketSchoolYear> getWarCustomerMarketSchoolYears() {
        return warCustomerMarketSchoolYears;
    }

    public void setWarCustomerMarketSchoolYears(Set<WarCustomerMarketSchoolYear> warCustomerMarketSchoolYears) {
        this.warCustomerMarketSchoolYears = warCustomerMarketSchoolYears;
    }

    public String getMarketDetail() {
        return marketDetail;
    }

    public void setMarketDetail(String marketDetail) {
        this.marketDetail = marketDetail;
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
