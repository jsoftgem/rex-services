package com.jsofttechnologies.rexwar.model.management;

import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 2/26/2015.
 */

@Entity
@Table(name = "war_agent")
@NamedQueries({
        @NamedQuery(name = WarAgentLight.FIND_ALL, query = "select a from WarAgentLight a"),
        @NamedQuery(name = WarAgentLight.FIND_AGENT_BY_MANAGER, query = "select a from WarAgentLight a where a.isManager = false and a.region =:region")
})
public class WarAgentLight implements FlowJpe {


    public static final String FIND_ALL = "WarAgentLight.FIND_ALL";
    public static final String FIND_AGENT_BY_MANAGER = "WarAgentLight.FIND_AGENT_BY_MANAGER";

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

    @Formula("(select fud.avatar from war_agent wa join flow_user fu on fu.flow_user_id = wa.user join flow_user_detail fud on fud.flow_user_detail_id = fu.flow_user_detail_id where wa.war_agent_id = war_agent_id )")
    private Long avatar;

    @Formula("(select fud.full_name from war_agent wa join flow_user fu on fu.flow_user_id = wa.user join flow_user_detail fud on fud.flow_user_detail_id = fu.flow_user_detail_id where wa.war_agent_id = war_agent_id )")
    private String fullName;


    @Override
    public void setId(Object id) {
        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    public Long getId() {
        return id;
    }


    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
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


    public Long getAvatar() {
        return avatar;
    }

    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public void prePersist() {

    }

    @Override
    public void preUpdate() {

    }
}
