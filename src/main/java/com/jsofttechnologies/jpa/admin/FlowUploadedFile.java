package com.jsofttechnologies.jpa.admin;

import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 12/14/2014.
 */
@Entity
@Table(name = "flow_uploaded_file")
@NamedQueries({
        @NamedQuery(name = FlowUploadedFile.FIND_BY_ID, query = "select fuf from  FlowUploadedFile fuf where fuf.id=:id"),
        @NamedQuery(name = FlowUploadedFile.FIND_ALL, query = "select fuf from  FlowUploadedFile fuf")
})
public class FlowUploadedFile implements FlowJpe {
    public static final String FIND_ALL = "FlowUploadedFile.FIND_ALL";
    public static final String FIND_BY_ID = "FlowUploadedFile.FIND_BY_ID";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_uploaded_file_id")
    private Long id;
    @Column(name = "flow_uploaded_file_path", nullable = false)
    private String path;
    @Column(name = "flow_uploaded_file_folder", nullable = false)
    private String folder;
    @Column(name = "flow_uploaded_file_contentType", nullable = false)
    private String contentType;
    @Column(name = "flow_uploaded_file_type", nullable = false)
    private String type;
    @Column(name = "flow_uploaded_file_name", nullable = false)
    private String name;
    @Column(name = "flow_uploaded_file_size")
    private Integer size;
    @Column(name = "flow_uploaded_file_created_dt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDt;
    @Column(name = "flow_uploaded_file_updated_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDt;
    @Column(name = "flow_uploaded_file_description")
    private String description;
    @Column(name = "flow_uploaded_file_star_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDt;
    @Column(name = "flow_uploaded_file_end_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDt;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public Date getCreatedDt() {
        return createdDt;
    }

    @Override
    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
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
    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @PrePersist
    @Override
    public void prePersist() {
        createdDt = new Date();
        path = "default_path";
        name = "default_name";
    }

    @PreUpdate
    public void preUpdate() {
        updatedDt = new Date();
    }

    @PostPersist
    public void postPersist() {

        this.name = "_" + this.type.trim() + "Id#" + this.id;
        this.path = "services/download_service/getContent/" + this.id.toString();
    }
}
