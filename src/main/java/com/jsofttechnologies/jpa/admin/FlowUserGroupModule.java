package com.jsofttechnologies.jpa.admin;

import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 11/20/2014.
 */
@Entity
@Table(name = "flow_user_group_module")
@NamedQueries({
        @NamedQuery(
                name = FlowUserGroupModule.FIND_ALL, query = "select fugm from FlowUserGroupModule fugm"
        ),
        @NamedQuery(
                name = FlowUserGroupModule.FIND_BY_USER_GROUP, query = "select fugm from FlowUserGroupModule fugm where fugm.groupName=:groupName"
        ),
        @NamedQuery(
                name = FlowUserGroupModule.FIND_BY_USER_GROUP_AND_TASK, query = "select fugm from FlowUserGroupModule fugm where fugm.groupName=:groupName and fugm.flowUserGroupTask.flowUserTaskId=:taskId"
        )
})
public class FlowUserGroupModule implements FlowJpe {
    public static final String FIND_ALL = "FlowUserGroupModule.FIND_ALL";
    public static final String FIND_BY_USER_GROUP = "FlowUserGroupModule.FIND_BY_USER_GROUP";
    public static final String FIND_BY_USER_GROUP_AND_TASK = "FlowUserGroupModule.FIND_BY_USER_GROUP_AND_TASK";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_user_group_module_id", nullable = false)
    private Long id;
    @Column(name = "flow_module_id", nullable = false, insertable = true, updatable = false)
    private Long flowModuleId;
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
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "flow_user_group_task_id", referencedColumnName = "flow_user_group_task_id")
    private FlowUserGroupTask flowUserGroupTask;
    @Column(name = "flow_group_name")
    private String groupName;

    @Override
    public void setId(Object id) {
        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setFlowModuleId(Long flowModuleId) {
        this.flowModuleId = flowModuleId;
    }

    public Long getFlowModuleId() {
        return flowModuleId;
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

    public void setFlowUserGroupTask(FlowUserGroupTask flowUserGroupTask) {
        this.flowUserGroupTask = flowUserGroupTask;
    }

    public FlowUserGroupTask getFlowUserGroupTask() {
        return flowUserGroupTask;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
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
}



