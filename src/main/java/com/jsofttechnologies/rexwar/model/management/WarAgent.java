package com.jsofttechnologies.rexwar.model.management;/**
 * Created by Jerico on 1/11/2015.
 */


import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 12/22/2014.
 */
@Entity
@Table(name = "war_agent")
@NamedQueries({
        @NamedQuery(name = WarAgent.FIND_ALL, query = "select a from WarAgent a"),
        @NamedQuery(name = WarAgent.FIND_AGENT_BY_USERNAME, query = "select a from WarAgent a where a.user.username=:username"),
        @NamedQuery(name = WarAgent.FIND_AGENT_BY_MANAGER, query = "select a from WarAgent a where a.isManager = false and a.region =:region"),
        @NamedQuery(name = WarAgent.FIND_MANAGER_BY_REGION, query = "select a from WarAgent a where a.isManager = true and a.region =:region"),
        @NamedQuery(name = WarAgent.FIND_BY_REGION, query = "select a from WarAgent a where a.region =:region"),
        @NamedQuery(name = WarAgent.FIND_BY_INITIALS, query = "select a from WarAgent a where lower(a.initials) =:initials"),

})
public class WarAgent implements FlowJpe {

    public static final String FIND_ALL = "WarAgent.FIND_ALL";
    public static final String FIND_AGENT_BY_USERNAME = "WarAgent.FIND_AGENT_BY_USERNAME";
    public static final String FIND_AGENT_BY_MANAGER = "WarAgent.FIND_AGENT_BY_MANAGER";
    public static final String FIND_MANAGER_BY_REGION = "WarAgent.FIND_MANAGER_BY_REGION";
    public static final String FIND_BY_REGION = "WarAgent.FIND_BY_REGION";
    public static final String FIND_BY_INITIALS = "WarAgent.FIND_BY_INITIALS";
    @Id
    @Column(name = "war_agent_id", nullable = false)
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
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "flow_user_id")
    private FlowUser user;
    @Column(name = "region", nullable = false)
    private String region;
    @Column(name = "manager", nullable = false, length = ColumnLengths.FLAG)
    private Boolean isManager;

    @Column(name = "war_agent_active", nullable = false, length = ColumnLengths.FLAG)
    private Boolean active;

    @Column(name = "war_agent_online", length = ColumnLengths.FLAG)
    private Boolean online;

    @Column(name = "war_agent_initials", nullable = false)
    private String initials;

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

    public FlowUser getUser() {
        return user;
    }

    public void setUser(FlowUser user) {
        this.user = user;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
        if (active == null) {
            this.active = false;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedDt = new Date();
    }
}
