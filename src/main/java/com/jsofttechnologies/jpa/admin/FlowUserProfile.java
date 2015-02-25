package com.jsofttechnologies.jpa.admin;

import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "FlowUserProfile")
@Table(name = "flow_user_profile")
@NamedQueries({@NamedQuery(name = FlowUserProfile.FIND_ALL, query = "select mu from FlowUserProfile mu"),
        @NamedQuery(name = FlowUserProfile.FIND_BY_NAME, query = "select fp from  FlowUserProfile  fp where fp.profileName=:profileName")})
public class FlowUserProfile implements FlowJpe {

    /**
     *
     */
    private static final long serialVersionUID = -7592153861684186093L;

    public static final String FIND_ALL = "FLowUserProfile.FIND_ALL";
    public static final String FIND_BY_NAME = "FlowUserProfile.FIND_BY_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_user_profile_id", nullable = false)
    private Long id;
    @Column(name = "profile_name", nullable = false,length = ColumnLengths.NAME)
    private String profileName;
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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FlowProfilePermission> flowProfilePermissions;

    public FlowUserProfile() {
    }

    public FlowUserProfile(String profileName) {
        this.profileName = profileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = Long.valueOf(id.toString());
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setFlowProfilePermissions(List<FlowProfilePermission> flowProfilePermissions) {
        this.flowProfilePermissions = flowProfilePermissions;
    }

    public List<FlowProfilePermission> getFlowProfilePermissions() {
        return flowProfilePermissions;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.profileName.toLowerCase());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FlowUserProfile other = (FlowUserProfile) obj;
        if (!Objects.equals(this.profileName.toLowerCase(), other.profileName.toLowerCase())) {
            return false;
        }
        return true;
    }
}
