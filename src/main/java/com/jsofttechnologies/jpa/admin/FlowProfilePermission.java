package com.jsofttechnologies.jpa.admin;

import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 11/28/2014.
 */
@Entity
@Table(name = "flow_profile_permission")
@NamedQueries({
        @NamedQuery(name = FlowProfilePermission.FIND_ALL, query = "select fpp from FlowProfilePermission fpp")
})
public class FlowProfilePermission implements FlowJpe {

    public static final String FIND_ALL = "FlowProfilePermission.FIND_ALL";
    public static final String FIND_BY_ID = "FlowProfilePermission.FIND_BY_ID";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_profile_permmission_id", nullable = false)
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
    @Column(name = "flow_page_id", nullable = false)
    private Long flowPageId;
    @Column(name = "flow_page_name", nullable = false, length = ColumnLengths.NAME)
    private String flowPageName;
    @Column(name = "flow_permission_put")
    private Boolean put;
    @Column(name = "flow_permission_get")
    private Boolean get;
    @Column(name = "flow_permission_post")
    private Boolean post;
    @Column(name = "flow_permission_delete")
    private Boolean del;

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

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public Long getFlowPageId() {
        return flowPageId;
    }

    public void setFlowPageId(Long flowPageId) {
        this.flowPageId = flowPageId;
    }

    public String getFlowPageName() {
        return flowPageName;
    }

    public void setFlowPageName(String flowPageName) {
        this.flowPageName = flowPageName;
    }

    public Boolean getPut() {
        return put;
    }

    public void setPut(Boolean put) {
        this.put = put;
    }

    public Boolean getGet() {
        return get;
    }

    public void setGet(Boolean get) {
        this.get = get;
    }

    public Boolean getPost() {
        return post;
    }

    public void setPost(Boolean post) {
        this.post = post;
    }

    @Override
    @PrePersist
    public void prePersist() {
        this.createdDt = new Date();
        if (put == null) {
            this.put = Boolean.FALSE;
        }

        if (this.post == null) {
            this.post = Boolean.FALSE;
        }

        if (this.get == null) {
            this.get = Boolean.FALSE;
        }

        if (this.del == null) {
            this.del = Boolean.FALSE;
        }
    }

    @Override
    @PreUpdate
    public void preUpdate() {
        this.updatedDt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlowProfilePermission)) return false;

        FlowProfilePermission that = (FlowProfilePermission) o;

        if (!flowPageId.equals(that.flowPageId)) return false;
        if (!flowPageName.equals(that.flowPageName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flowPageId.hashCode();
        result = 31 * result + flowPageName.hashCode();
        return result;
    }
}
