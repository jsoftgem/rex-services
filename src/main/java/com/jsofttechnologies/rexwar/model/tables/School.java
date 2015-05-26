package com.jsofttechnologies.rexwar.model.tables;/**
 * Created by Jerico on 1/9/2015.
 */


import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 12/22/2014.
 */
@Entity
@Table(name = "war_customer_school")
@NamedQueries({
        @NamedQuery(name = School.FIND_ALL, query = "select sc from School sc"),
        @NamedQuery(name = School.FIND_BY_SCHOOL_NAME, query = "select sc from School sc where lower(sc.name)=lower(:name)")
})
public class School implements FlowJpe {

    public static final String FIND_ALL = "School.FIND_ALL";
    public static final String FIND_BY_SCHOOL_NAME = "School.FIND_BY_SCHOOL_NAME";
    @Id
    @Column(name = "war_customer_school_id", nullable = false)
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
    @Column(name = "school_name", nullable = false)
    private String name;
    @Column(name = "school_address_line1")
    private String addressLine1;
    @Column(name = "school_address_line2")
    private String addressLine2;
    @Column(name = "school_landline", length = 150)
    private String landline;
    @Column(name = "school_email", length = ColumnLengths.TITLE)
    private String email;
    @Column(name = "school_logo")
    private Long logoId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getLogoId() {
        return logoId;
    }

    public void setLogoId(Long logoId) {
        this.logoId = logoId;
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
