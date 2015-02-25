package com.jsofttechnologies.jpa.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "FlowUserDetail")
@Table(name = "flow_user_detail")
@NamedQueries({

        @NamedQuery(name = FlowUserDetail.FIND_ALL, query = "select mud from FlowUserDetail mud"),
        @NamedQuery(name = FlowUserDetail.FIND_BY_ID, query = "select mud from FlowUserDetail mud where mud.id =:userId")

})
public class FlowUserDetail implements FlowJpe {

    public static final String FIND_ALL = "MergeUserDetail.FIND_ALL";
    public static final String FIND_BY_ID = "MergeUserDetail.FIND_BY_ID";
    /**
     *
     */
    private static final long serialVersionUID = -7376912199764407770L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_user_detail_id", nullable = false)
    private Long id;
    @Column(name = "full_name", nullable = false, length = ColumnLengths.TITLE)
    private String fullName;
    @Column(name = "avatar")
    private Long avatar;
    @Column(name = "secret_question", nullable = false)
    private String secretQuestion;
    @Column(name = "secret_answer", nullable = false)
    private String secretAnswer;
    @JsonIgnore
    @OneToOne(mappedBy = "flowUserDetail", fetch = FetchType.LAZY)
    private FlowUser flowUser;
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


    @Override
    public void setId(Object id) {

        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    @Override
    public Long getId() {

        return id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }

    public Long getAvatar() {
        return avatar;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setFlowUser(FlowUser flowUser) {
        this.flowUser = flowUser;
    }

    public FlowUser getFlowUser() {
        return flowUser;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlowUserDetail)) return false;

        FlowUserDetail that = (FlowUserDetail) o;

        if (flowUser != null ? !flowUser.equals(that.flowUser) : that.flowUser != null) return false;
        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (flowUser != null ? flowUser.hashCode() : 0);
        return result;
    }
}
