package com.jsofttechnologies.jpa.dev;

import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "flow_style", uniqueConstraints = {@UniqueConstraint(columnNames = {"flow_style_name"})})
@NamedQueries({@NamedQuery(name = FlowStyle.FIND_ALL, query = "select fs from FlowStyle fs"),
        @NamedQuery(name = FlowStyle.FIND_BY_COMP_ID_AND_TYPE, query = "select fs from FlowStyle fs, FlowStyleAsc fsa where fs.id = fsa.styleID and fsa.flowCompId=:compId and fsa.type=:compType")})
public class FlowStyle implements FlowJpe {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "FlowStyle.FIND_ALL";
    public static final String FIND_BY_COMP_ID_AND_TYPE = "FlowStyle.FIND_BY_COMP_ID_AND_TYPE";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_style_id", nullable = false)
    private Long id;
    @Column(name = "flow_style_name", nullable = false, unique = true)
    private String name;
    @Column(name = "flow_style_field", nullable = false)
    private String field;
    @Column(name = "flow_style_value")
    private String value;
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        if (!(o instanceof FlowStyle)) return false;

        FlowStyle flowStyle = (FlowStyle) o;

        if (!name.equals(flowStyle.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
