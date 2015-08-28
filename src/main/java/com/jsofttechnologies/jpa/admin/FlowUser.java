package com.jsofttechnologies.jpa.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.report.utlil.ReportColumn;
import com.jsofttechnologies.report.utlil.ReportHeader;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.security.Principal;
import java.util.Date;
import java.util.Set;

@Entity(name = "FlowUser")
@Table(name = "flow_user", uniqueConstraints = @UniqueConstraint(columnNames = {"flow_username", "flow_email"}))
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = FlowUser.FIND_ALL, query = "select u from FlowUser u"),
        @NamedQuery(name = FlowUser.FIND_BY_USERNAME, query = "select u from FlowUser u where u.username = :username"),
        @NamedQuery(name = FlowUser.FIND_BY_EMAIL, query = "select u from FlowUser u where u.email = :email"),
        @NamedQuery(name = FlowUser.FIND_BY_ID, query = "select u from FlowUser  u where u.id=:id")})
@ReportHeader(name = "Flow User")
public class FlowUser implements FlowJpe, Principal {

    /**
     *
     */
    private static final long serialVersionUID = -5161405749179667892L;
    public static final String FIND_ALL = "FlowUser.FIND_ALL";
    public static final String FIND_BY_ID = "FlowUser.FIND_BY_ID";
    public static final String FIND_BY_USERNAME = "FlowUser.FIND_BY_USERNAME";
    public static final String FIND_BY_EMAIL = "FlowUser.FIND_BY_EMAIL";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_user_id", nullable = false)
    private Long id;
    @ReportColumn(name = "Username")
    @Column(name = "flow_username", nullable = false, unique = true)
    private String username;

    @Column(name = "flow_password", nullable = false, columnDefinition = "TEXT")
    private String password;
    @ReportColumn(name = "Email")
    @Column(name = "flow_email", nullable = false, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "flow_user_detail_id", referencedColumnName = "flow_user_detail_id")
    private FlowUserDetail flowUserDetail;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<FlowUserProfile> flowUserProfileSet;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_user_group_id", nullable = false)
    private FlowUserGroup flowUserGroup;
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
    @Transient
    @JsonProperty
    private String group;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = Long.valueOf(id.toString());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFlowUserDetail(FlowUserDetail flowUserDetail) {
        this.flowUserDetail = flowUserDetail;
    }

    public FlowUserDetail getFlowUserDetail() {
        return flowUserDetail;
    }

    public void setFlowUserProfileSet(Set<FlowUserProfile> flowUserProfileSet) {
        this.flowUserProfileSet = flowUserProfileSet;
    }

    public Set<FlowUserProfile> getFlowUserProfileSet() {
        return flowUserProfileSet;
    }

    public FlowUserGroup getFlowUserGroup() {
        return flowUserGroup;
    }

    public void setFlowUserGroup(FlowUserGroup flowUserGroup) {
        this.flowUserGroup = flowUserGroup;
        if (this.flowUserGroup != null) {
            this.group = this.flowUserGroup.getGroupTitle();
        }
    }

    @Override
    @JsonIgnore
    public String getName() {
        return username;
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
    public String toString() {
        return "FlowUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", flowUserDetail=" + flowUserDetail +
                ", flowUserProfileSet=" + flowUserProfileSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlowUser)) return false;

        FlowUser flowUser = (FlowUser) o;

        if (email != null ? !email.equals(flowUser.email) : flowUser.email != null) return false;
        if (username != null ? !username.equals(flowUser.username) : flowUser.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
