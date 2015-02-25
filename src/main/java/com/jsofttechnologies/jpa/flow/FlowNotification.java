package com.jsofttechnologies.jpa.flow;

import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 12/22/2014.
 */
@Entity
@Table(name = "flow_notification")
@NamedQueries({
        @NamedQuery(name = FlowNotification.FIND_ALL, query = "select fn from FlowNotification fn"),
        @NamedQuery(name = FlowNotification.FIND_UNNOTICED, query = "select fn from FlowNotification  fn where fn.notified=false order by fn.createdDt desc"),
        @NamedQuery(name = FlowNotification.FIND_UNNOTICED_GROUP, query = "select fn from FlowNotification  fn where fn.notified=false and fn.group=:group order by fn.createdDt desc"),
        @NamedQuery(name = FlowNotification.FIND_UNNOTICED_USER, query = "select fn from FlowNotification fn where fn.notified = false and fn.userId=:userId order by fn.createdDt desc"),
        @NamedQuery(name = FlowNotification.FIND_GROUP, query = "select fn from FlowNotification  fn where fn.group=:group order by fn.startDt desc"),
        @NamedQuery(name = FlowNotification.FIND_USER, query = "select fn from FlowNotification fn where fn.userId=:userId order by fn.startDt desc")
})
public class FlowNotification implements FlowJpe {
    public static final String FIND_ALL = "FlowNotification.FIND_ALL";
    public static final String FIND_UNNOTICED = "FlowNotification.FIND_UNNOTICED";
    public static final String FIND_GROUP = "FlowNotification.FIND_GROUP";
    public static final String FIND_USER = "FlowNotification.FIND_USER";
    public static final String FIND_UNNOTICED_GROUP = "FlowNotification.FIND_UNNOTICED_GROUP";
    public static final String FIND_UNNOTICED_USER = "FlowNotification.FIND_UNNOTICED_USER";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_notification_id", nullable = false)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_dt", nullable = false)
    private Date createdDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_dt")
    private Date updatedDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_dt", nullable = false)
    private Date startDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_dt")
    private Date endDt;
    @Column(name = "description")
    private String description;
    @Column(name = "message", length = ColumnLengths.MESSAGE, nullable = false)
    private String message;
    @Column(name = "message_type", nullable = false, length = ColumnLengths.TYPE)
    private String messageType;
    @Column(name = "group_name", nullable = false, length = ColumnLengths.NAME)
    private String group;
    @Column(name = "task", length = ColumnLengths.NAME)
    private String task;
    @Column(name = "page", length = ColumnLengths.NAME)
    private String page;
    @Column(name = "page_param", length = ColumnLengths.PARAM)
    private String pageParam;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "session_id")
    private Long sessionId;
    @Column(name = "notified", nullable = false)
    private Boolean notified;
    @Column(name = "alarm_type", nullable = false, length = ColumnLengths.TYPE)
    private String alarmType;
    @Column(name = "profiles", nullable = false)
    private String profiles;
    @Column(name = "global", nullable = false, length = ColumnLengths.FLAG)
    private Boolean global;
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


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageParam() {
        return pageParam;
    }

    public void setPageParam(String pageParam) {
        this.pageParam = pageParam;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getNotified() {
        return notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getProfiles() {
        return profiles;
    }

    public void setProfiles(String profiles) {
        this.profiles = profiles;
    }

    public Boolean getGlobal() {
        return global;
    }

    public void setGlobal(Boolean global) {
        this.global = global;
    }

    @PrePersist
    public void prePersist() {
        if (notified == null) notified = false;
        createdDt = new Date();
    }

    @Override
    public void preUpdate() {
        if (notified == null) notified = false;
        updatedDt = new Date();
    }
}
