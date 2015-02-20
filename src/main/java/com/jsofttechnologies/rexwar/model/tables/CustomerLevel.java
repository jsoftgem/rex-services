package com.jsofttechnologies.rexwar.model.tables;

import com.jsofttechnologies.jpa.util.FlowJpe;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 1/15/2015.
 */
@Entity
@Table(name = "war_customer_level")
@NamedQueries({
        @NamedQuery(name = CustomerLevel.FIND_ALL, query = "select e from CustomerLevel e")
})
public class CustomerLevel implements FlowJpe {

    public static final String FIND_ALL = "CustomerLevel.FIND_ALL";

    @Id
    @Column(name = "war_customer_level_id", nullable = false)
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
    @Column(name = "war_customer_level_population")
    private Integer population;
    @Column(name = "war_level_tuition_fee_from")
    private Double tuitionFeeFrom;
    @Column(name = "war_level_tuition_fee_to")
    private Double tuitionFeeTo;
    @Column(name = "level_id")
    private Long level;
    @Formula("(select l.description from war_customer_contacts_level l where l.level_id = level_id)")
    private String educationLevel;
    @Formula("(select l.level_course from war_customer_contacts_level l where l.level_id = level_id)")
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

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Double getTuitionFeeFrom() {
        return tuitionFeeFrom;
    }

    public void setTuitionFeeFrom(Double tuitionFeeFrom) {
        this.tuitionFeeFrom = tuitionFeeFrom;
    }

    public Double getTuitionFeeTo() {
        return tuitionFeeTo;
    }

    public void setTuitionFeeTo(Double tuitionFeeTo) {
        this.tuitionFeeTo = tuitionFeeTo;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
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
