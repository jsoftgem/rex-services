package com.jsofttechnologies.jpa.admin;

import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 7/23/2014.
 */
@Entity(name = "FlowSummary")
@Table(name = "flow_summary")
@NamedQueries({

        @NamedQuery(name = FlowSummary.FIND_ALL, query = "select ms from FlowSummary ms")

})
public class FlowSummary implements FlowJpe {

    /**
     *
     */
    private static final long serialVersionUID = 4073706653495675762L;

    public static final String FIND_ALL = "FlowSummary.FIND_ALL";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_summary_id", nullable = false)
    private Long id;
    @Column(name = "execution_time", nullable = false)
    private Long execTime;
    @Column(name = "object_details")
    private String objectDetails;
    @Column(name = "session_id", nullable = false)
    private String sessionId;
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
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    public Long getExecTime() {
        return execTime;
    }

    public void setExecTime(Long execTime) {
        this.execTime = execTime;
    }

    public String getObjectDetails() {
        return objectDetails;
    }

    public void setObjectDetails(String objectDetails) {
        this.objectDetails = objectDetails;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
