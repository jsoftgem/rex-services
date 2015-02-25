package com.jsofttechnologies.jpa.admin;

import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

@Entity()
@Table(name = "flow_session")
@NamedQueries({
        @NamedQuery(name = FlowSession.FIND_ALL, query = "select ms from FlowSession ms"),
        @NamedQuery(name = FlowSession.FIND_BY_USER_HOST, query = "select ms from FlowSession ms where ms.userId = :userId and ms.userHost= :userHost"),
        @NamedQuery(name = FlowSession.FIND_BY_ACTIVE_BY_USER, query = "select fs from FlowSession fs where fs.userId=:userId and fs.active=true order by fs.createdDt desc")
})

public class FlowSession implements FlowJpe {

    /**
     *
     */
    private static final long serialVersionUID = -4445906012022842878L;

    public static final String FIND_ALL = "FlowSession.FIND_ALL";
    public static final String FIND_BY_SESSIONID = "FlowSession.FIND_BY_SESSIONID";
    public static final String FIND_BY_USER_HOST = "FlowSession.FIND_BY_USER_HOST";
    public static final String FIND_BY_ACTIVE_BY_USER = "FlowSession.FIND_BY_ACTIVE_BY_USER";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_session_id", nullable = false)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_access_date", nullable = false)
    private Date lastAccessDate;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "active", nullable = false)
    private Boolean active;
    @Column(name = "secured", nullable = false)
    private Boolean secured;
    @Column(name = "user_host", nullable = false)
    private String userHost;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDt;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDt;
    @Column(name = "usage_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDt;
    @Column(name = "usage_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDt;
    @Column(name = "description")
    private String description;


    @Override
    public void setId(Object id) {
        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    public void setLastAccessDate(Date lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public Date getLastAccessDate() {
        return lastAccessDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setSecured(Boolean secured) {
        this.secured = secured;
    }

    public Boolean getSecured() {
        return secured;
    }

    public void setUserHost(String userHost) {
        this.userHost = userHost;
    }

    public String getUserHost() {
        return userHost;
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
    @Override
    public void prePersist() {
        
        createdDt = new Date();
    }

    @PreUpdate
    @Override
    public void preUpdate() {
        
        updatedDt = new Date();
    }

}
