package com.jsofttechnologies.jpa.admin;

import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Jerico on 11/2/2014.
 */
@Entity(name = "FlowUserGroup")
@Table(name = "flow_user_group")
@NamedQueries({
        @NamedQuery(name = FlowUserGroup.FIND_ALL, query = "select fug from FlowUserGroup fug"),
        @NamedQuery(name = FlowUserGroup.FIND_GROUP_BY_USER_ID, query = "select fug from FlowUserGroup fug, FlowUser fu where fug.id = fu.flowUserGroup.id and fu.id=:userId"),
        @NamedQuery(name = FlowUserGroup.FIND_GROUP_BY_NAME, query = "select fug from FlowUserGroup fug where fug.groupName =:groupName")
})
public class FlowUserGroup implements FlowJpe {

    public static final String FIND_ALL = "FlowUserGroup.FIND_ALL";
    public static final String FIND_GROUP_BY_USER_ID = "FlowUserGroup.FIND_GROUP_BY_USER_ID";
    public static final String FIND_GROUP_BY_NAME = "FlowUserGroup.FIND_GROUP_BY_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_user_group_id", nullable = false)
    private Long id;

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

    @Column(name = "flow_user_group_name", nullable = false, length = ColumnLengths.NAME)
    private String groupName;

    @Column(name = "flow_user_group_title", nullable = false, length = ColumnLengths.TITLE)
    private String groupTitle;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<FlowUser> flowUsers;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FlowUserGroupModule> flowUserGroupModules;

    @Column(name = "owner_user_id", nullable = false)
    private Long ownerUserId;

    @Column(name = "group_admin_fullname", nullable = false, length = ColumnLengths.TITLE)
    private String groupAdminFullname;


    @Column(name = "emails", nullable = false)
    private String emails;
    @Column(name = "emblem_id")
    private Long emblemId;
    @Override
    public void setId(Object id) {
        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

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


    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setFlowUsers(Set<FlowUser> flowUsers) {
        this.flowUsers = flowUsers;
    }

    public Set<FlowUser> getFlowUsers() {
        return flowUsers;
    }

    public void setFlowUserGroupModules(Set<FlowUserGroupModule> flowUserGroupModules) {
        this.flowUserGroupModules = flowUserGroupModules;
    }

    public Set<FlowUserGroupModule> getFlowUserGroupModules() {
        return flowUserGroupModules;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlowUserGroup)) return false;

        FlowUserGroup that = (FlowUserGroup) o;

        if (!groupName.equals(that.groupName)) return false;

        return true;
    }

    public Long getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getGroupAdminFullname() {
        return groupAdminFullname;
    }

    public void setGroupAdminFullname(String groupAdminFullname) {
        this.groupAdminFullname = groupAdminFullname;
    }

    public void setEmblemId(Long emblemId) {
        this.emblemId = emblemId;
    }

    public Long getEmblemId() {
        return emblemId;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getEmails() {
        return emails;
    }

    @Override
    public int hashCode() {
        return groupName.hashCode();
    }
}
