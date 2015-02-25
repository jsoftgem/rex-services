package com.jsofttechnologies.jpa.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 11/2/2014.
 */
@Entity(name = "FlowUserGroupTask")
@Table(name = "flow_user_group_task")
@NamedQueries(
        {
                @NamedQuery(name = FlowUserGroupTask.FIND_ALL, query = "select fugt from FlowUserGroupTask fugt")
        }
)
public class FlowUserGroupTask implements FlowJpe {


    public static final String FIND_ALL = "FlowUserGroupTask.FIND_ALL";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_user_group_task_id", nullable = false)
    private Long id;
    @Column(name = "flow_user_task_id", nullable = false, insertable = true, updatable = false)
    private Long flowUserTaskId;
    @Column(name = "created_dt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDt;
    @Column(name = "updated_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDt;
    @Column(name = "start_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDt;
    @Column(name = "end_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDt;
    @Column(name = "description")
    private String description;
    @Column(name = "flow_user_group_size", length = 2)
    private Integer size;
    @Column(name = "flow_user_group_active", length = 1)
    private Boolean active;
    @Column(name = "flow_user_group_locked", length = 1)
    private Boolean locked;
    @Column(name = "flow_user_group_pinned", length = 1)
    private Boolean pinned;
    @Column(name = "flow_task_page")
    private String page;
    @Column(name = "flow_task_param")
    private String param;
    @Column(name = "flow_user_group_closed", length = 1)
    private Boolean closed;
    @Column(name = "flow_user_toolbar", length = 1)
    private Boolean toolBar;

    @JsonIgnore
    @OneToOne(mappedBy = "flowUserGroupTask", fetch = FetchType.LAZY)
    private FlowUserGroupModule flowUserGroupModule;

    @Override
    public void setId(Object id) {
        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setFlowUserTaskId(Long flowUserTaskId) {
        this.flowUserTaskId = flowUserTaskId;
    }

    public Long getFlowUserTaskId() {
        return flowUserTaskId;
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

    public Boolean getPinned() {
        return pinned;
    }

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setToolBar(Boolean toolBar) {
        this.toolBar = toolBar;
    }

    public Boolean getToolBar() {
        return toolBar;
    }

    @Override
    @PrePersist
    public void prePersist() {
        this.createdDt = new Date();
    }

    @PreUpdate
    @Override
    public void preUpdate() {
        this.updatedDt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlowUserGroupTask)) return false;

        FlowUserGroupTask that = (FlowUserGroupTask) o;

        if (flowUserTaskId != null ? !flowUserTaskId.equals(that.flowUserTaskId) : that.flowUserTaskId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return flowUserTaskId != null ? flowUserTaskId.hashCode() : 0;
    }
}
