package com.jsofttechnologies.rexwar.model.management;

import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.rexwar.model.tables.School;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 1/27/2015 15:22.
 */
@Entity
@Table(name = "war_customer")
@NamedQueries({
        @NamedQuery(name = WarCustomerLight.FIND_ALL, query = "select e from WarCustomerLight e")
})
public class WarCustomerLight implements FlowJpe {

    public static final String FIND_ALL = "WarCustomerLight.FIND_ALL";

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

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
    }

    @Override
    public void preUpdate() {
        updatedDt = new Date();
    }
}
