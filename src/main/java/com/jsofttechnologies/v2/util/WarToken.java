package com.jsofttechnologies.v2.util;

import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.jpa.admin.FlowUserGroupModule;
import com.jsofttechnologies.jpa.admin.FlowUserProfile;
import com.jsofttechnologies.jpa.dev.FlowGroup;
import com.jsofttechnologies.jpa.dev.FlowModule;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by rickzx98 on 8/30/15.
 */
@XmlRootElement
public class WarToken implements Serializable {


    private Date created;
    private String username;
    private Long userId;
    private Long avatarId;
    private Set<FlowUserProfile> flowUserProfiles;
    private String group;
    private Long flowUserDetailId;
    private List<FlowGroup>flowGroups;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public Set<FlowUserProfile> getFlowUserProfiles() {
        return flowUserProfiles;
    }

    public void setFlowUserProfiles(Set<FlowUserProfile> flowUserProfiles) {
        this.flowUserProfiles = flowUserProfiles;
    }


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getFlowUserDetailId() {
        return flowUserDetailId;
    }

    public void setFlowUserDetailId(Long flowUserDetailId) {
        this.flowUserDetailId = flowUserDetailId;
    }

    public List<FlowGroup> getFlowGroups() {
        return flowGroups;
    }

    public void setFlowGroups(List<FlowGroup> flowGroups) {
        this.flowGroups = flowGroups;
    }
}