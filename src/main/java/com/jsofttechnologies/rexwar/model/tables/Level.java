package com.jsofttechnologies.rexwar.model.tables;/**
 * Created by Jerico on 1/8/2015.
 */


import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 12/22/2014.
 */
@Entity
@Table(name = "war_customer_contacts_level")
@NamedQueries({
        @NamedQuery(name = Level.FIND_ALL, query = "select lv from Level lv"),
        @NamedQuery(name = Level.FIND_ALL_EDUCATION_LEVEL, query = "select distinct lv.description from Level lv")
})
public class Level implements FlowJpe {

    public static final String FIND_ALL = "Level.FIND_ALL";
    public static final String FIND_ALL_EDUCATION_LEVEL = "Level.FIND_ALL_EDUCATION_LEVEL";

    @Id
    @Column(name = "level_id", nullable = false)
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
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "level_course", nullable = false, length = ColumnLengths.TITLE)
    private String levelCourse;

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

    public String getLevelCourse() {
        return levelCourse;
    }

    public void setLevelCourse(String levelCourse) {
        this.levelCourse = levelCourse;
    }

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
    }

    @Override
    public void preUpdate() {
        updatedDt = new Date();
    }
}
