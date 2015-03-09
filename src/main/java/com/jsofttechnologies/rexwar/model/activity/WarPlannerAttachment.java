package com.jsofttechnologies.rexwar.model.activity;

import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 3/8/2015.
 */
@Entity
@Table(name = "war_planner_attachment")
@NamedQueries({
        @NamedQuery(name = WarPlannerAttachment.FIND_ALL, query = "select e from WarPlannerAttachment e"),
        @NamedQuery(name = WarPlannerAttachment.FIND_ALL_SCHOOL_YEAR, query = "select e from WarPlannerAttachment e where e.schoolYear=:schoolYear")
})
public class WarPlannerAttachment implements FlowJpe {

    public static final String FIND_ALL = "WarPlannerAttachment.FIND_ALL";
    public static final String FIND_ALL_SCHOOL_YEAR = "WarPlannerAttachment.FIND_ALL_SCHOOL_YEAR";

    @Id
    @Column(name = "war_planner_attachment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Column(name = "description")
    private String description;
    @Column(name = "war_planner_attachment_school_year", nullable = false)
    private Long schoolYear;
    @Column(name = "war_planner_attachment_file_name", nullable = false)
    private String fileName;
    @Column(name = "war_planner_attachment_file_type")
    private String fileType;
    @Column(name = "war_planner_attachment_file_done")
    private Boolean done;
    @Column(name = "war_planner_attachment_uploaded_file", nullable = false)
    private Long uploadedFileId;

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

    public Long getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Long schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Long getUploadedFileId() {
        return uploadedFileId;
    }

    public void setUploadedFileId(Long uploadedFileId) {
        this.uploadedFileId = uploadedFileId;
    }

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDt = new Date();
    }
}
