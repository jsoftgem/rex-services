package com.jsofttechnologies.rexwar.model.tables;/**
 * Created by Jerico on 1/8/2015.
 */


import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.rexwar.util.contants.ContactType;
import com.jsofttechnologies.rexwar.util.contants.RelationType;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 12/22/2014.
 */
@Entity
@Table(name = "war_customer_contacts")
@NamedQueries({
        @NamedQuery(name = Contacts.FIND_ALL, query = "select c from Contacts c")
})
public class Contacts implements FlowJpe {

    public static final String FIND_ALL = "Contacts.FIND_ALL";

    @Id
    @Column(name = "contact_id", nullable = false)
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
    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type", length = ColumnLengths.TYPE)
    private ContactType type;
    @Column(name = "contact_detail")
    private String detail;
    @Column(name = "contact_level", nullable = false)
    private String level;
    @Column(name = "contact_position", nullable = false)
    private Long position;
    @Temporal(TemporalType.DATE)
    @Column(name = "contact_birthdate", nullable = false)
    private Date birthdate;
    @Column(name = "decision_maker", nullable = false)
    private Boolean decisionMaker;
    @Column(name = "relationship_type", nullable = false)
    private String relationshipType;
    @Formula("(select pos.description from war_customer_contacts_position pos where pos.position_id = contact_position)")
    private String positionDesc;

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

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Boolean getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Boolean decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc;
    }

    @PrePersist
    public void prePersist() {
        if (decisionMaker == null) {
            decisionMaker = Boolean.FALSE;
        }

        if (relationshipType == null) {
            relationshipType = RelationType.NONE.toString();
        }
        createdDt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDt = new Date();
    }
}
