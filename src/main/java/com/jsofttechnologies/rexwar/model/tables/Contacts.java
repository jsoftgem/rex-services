package com.jsofttechnologies.rexwar.model.tables;/**
 * Created by Jerico on 1/8/2015.
 */


import com.jsofttechnologies.jpa.util.FlowJpe;
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
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "contact_detail")
    private String detail;
    @Column(name = "contact_level", nullable = false)
    private String level;
    @Column(name = "contact_position", nullable = false)
    private Long position;
    @Temporal(TemporalType.DATE)
    @Column(name = "contact_birthdate")
    private Date birthdate;
    @Column(name = "decision_maker")
    private Boolean decisionMaker;
    @Column(name = "relationship_type")
    private String relationshipType;
    @Formula("(select pos.description from war_customer_contacts_position pos where pos.position_id = contact_position)")
    private String positionDesc;
    @Column(name = "contact_home")
    private String homePhone;
    @Column(name = "contact_work")
    private String workPhone;
    @Column(name = "contact_email")
    private String email;
    @Column(name = "contact_yahoo")
    private String yahoo;
    @Column(name = "contact_skype")
    private String skype;
    @Column(name = "contact_mobile")
    private String mobile;

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

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYahoo() {
        return yahoo;
    }

    public void setYahoo(String yahoo) {
        this.yahoo = yahoo;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

        StringBuilder contactDetails = new StringBuilder();

        if (homePhone != null) {
            contactDetails.append("Home: ").append(homePhone).append("\n");
        }

        if (workPhone != null) {
            contactDetails.append("Work: ").append(workPhone).append("\n");
        }

        if (email != null) {
            contactDetails.append("Email: ").append(email).append("\n");
        }
        if (yahoo != null) {
            contactDetails.append("Yahoo Messenger: ").append(yahoo).append("\n");
        }
        if (skype != null) {
            contactDetails.append("Skype: ").append(skype).append("\n");
        }
        this.detail = contactDetails.toString();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDt = new Date();
        StringBuilder contactDetails = new StringBuilder();

        if (homePhone != null) {
            contactDetails.append("Home: ").append(homePhone).append("\n");
        }

        if (workPhone != null) {
            contactDetails.append("Work: ").append(workPhone).append("\n");
        }

        if (email != null) {
            contactDetails.append("Email: ").append(email).append("\n");
        }
        if (yahoo != null) {
            contactDetails.append("Yahoo Messenger: ").append(yahoo).append("\n");
        }
        if (skype != null) {
            contactDetails.append("Skype: ").append(skype).append("\n");
        }
        this.detail = contactDetails.toString();
    }
}
