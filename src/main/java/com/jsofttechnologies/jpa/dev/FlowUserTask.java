package com.jsofttechnologies.jpa.dev;

import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 10/30/2014.
 */
@Entity(name = "FlowUserTask")
@Table(name = "flow_user_task")
@NamedQueries(
        {@NamedQuery(name = FlowUserTask.FIND_ALL, query = "select fut from FlowUserTask fut"),
                @NamedQuery(name = FlowUserTask.FIND_BY_TASK_AND_USER, query = "select fut from FlowUserTask fut where fut.flowTaskId=:taskId and fut.flowUserId=:userId"),
                @NamedQuery(name = FlowUserTask.FIND_BY_USER, query = "select fut from FlowUserTask fut where fut.flowUserId=:userId")
                , @NamedQuery(name = FlowUserTask.FIND_BY_ID, query = "Select fut from FlowUserTask fut where fut.id =:id")}
)
public class FlowUserTask implements FlowJpe {

    public static final String FIND_ALL = "FlowUserTask.FIND_ALL";
    public static final String FIND_BY_ID = "FlowUserTask.FIND_BY_ID";
    public static final String FIND_BY_TASK_AND_USER = "FlowUserTask.FIND_BY_TASK_AND_USER";
    public static final String FIND_BY_USER = "FlowUserTask.FIND_BY_USER";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_user_task_id", nullable = false)
    private Long id;

    @Column(name = "flow_user_id", nullable = false, updatable = false)
    private Long flowUserId;

    @Column(name = "flow_task_id", nullable = false, updatable = false)
    private Long flowTaskId;

    @Column(name = "description")
    private String description;

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

    @Column(name = "flow_task_size", length = 2)
    private Integer size;

    @Column(name = "flow_task_active",length = ColumnLengths.FLAG)
    private Boolean active;

    @Column(name = "flow_task_locked", length = ColumnLengths.FLAG)
    private Boolean locked;

    @Column(name = "flow_task_pinned", length = ColumnLengths.FLAG)
    private Boolean pinned;

    @Column(name = "flow_task_page", length = ColumnLengths.NAME)
    private String page;
    @Column(name = "flow_task_param", length = ColumnLengths.PARAM)
    private String param;
    @Column(name = "flow_task_closed", length = ColumnLengths.FLAG)
    private Boolean closed;
    @Column(name = "flow_id")
    private String flowId;

    public void setId(Object id) {
        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setFlowUserId(Long flowUserId) {
        this.flowUserId = flowUserId;
    }

    public Long getFlowUserId() {
        return flowUserId;
    }

    public void setFlowTaskId(Long flowTaskId) {
        this.flowTaskId = flowTaskId;
    }

    public Long getFlowTaskId() {
        return flowTaskId;
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


    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }

    public Boolean getPinned() {
        return pinned;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowId() {
        return flowId;
    }

    @PrePersist
    @Override
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
        if (!(o instanceof FlowUserTask)) return false;

        FlowUserTask that = (FlowUserTask) o;

        if (!flowTaskId.equals(that.flowTaskId)) return false;
        if (!flowUserId.equals(that.flowUserId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flowUserId.hashCode();
        result = 31 * result + flowTaskId.hashCode();
        return result;
    }
}
