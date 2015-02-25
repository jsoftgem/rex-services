package com.jsofttechnologies.jpa.dev;

import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 11/11/2014.
 */
@Entity
@Table(name = "flow_style_asc", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"flow_comp_id", "flow_comp_type", "flow_style_id"})
})
@NamedQueries({
        @NamedQuery(name = FlowStyleAsc.FIND_ALL, query = "select fta from FlowStyleAsc fta"),
        @NamedQuery(name = FlowStyleAsc.FIND_BY_COMP_ID_AND_TYPE, query = "select fta from FlowStyleAsc fta where fta.flowCompId=:compId and fta.type=:compType")
})
public class FlowStyleAsc implements FlowJpe {
    public static final String FIND_ALL = "FlowStyleAsc.FIND_ALL";
    public static final String FIND_BY_COMP_ID_AND_TYPE = "FlowStyleAsc.FIND_BY_COMP_ID_AND_TYPE";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_style_asc_id", nullable = false)
    private Long id;
    @Column(name = "flow_comp_id", nullable = false)
    private Long flowCompId;
    @Column(name = "flow_comp_type", nullable = false)
    private String type;
    @Column(name = "flow_style_id", nullable = false)
    private Long styleID;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_dt", nullable = false)
    private Date createdDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_dt")
    private Date updatedDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_dn")
    private Date startDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_dt")
    private Date endDt;
    @Column(name = "description")
    private String description;

    @Override
    public void setId(Object id) {
        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getFlowCompId() {
        return flowCompId;
    }

    public void setFlowCompId(Long flowCompId) {
        this.flowCompId = flowCompId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getStyleID() {
        return styleID;
    }

    public void setStyleID(Long styleID) {
        this.styleID = styleID;
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
        return getUpdatedDt();
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

    @Override
    @PrePersist
    public void prePersist() {
        this.createdDt = new Date();
    }

    @Override
    @PreUpdate
    public void preUpdate() {
        this.updatedDt = new Date();
    }
}
