package com.jsofttechnologies.jpa.dev;

import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "flow_group", uniqueConstraints = {@UniqueConstraint(columnNames = {"group_name"})})
@NamedQueries({@NamedQuery(name = FlowGroup.FIND_ALL, query = "select fg from FlowGroup fg"),
        @NamedQuery(name = FlowGroup.FIND_BY_GROUP_NAME, query = "select fg from FlowGroup fg where fg.name=:name")})
public class FlowGroup implements FlowJpe {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "FlowGroup.FIND_ALL";
    public static final String FIND_BY_GROUP_NAME = "FlowGroup.FIND_BY_GROUP_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Long id;
    @Column(name = "group_name", nullable = false, unique = true, length = ColumnLengths.NAME)
    private String name;
    @Column(name = "group_title", nullable = false, length = ColumnLengths.TITLE)
    private String title;
    @Column(name = "group_icon_uri")
    private String iconUri;
    @Column(name = "group_index", nullable = false)
    private Integer index;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<FlowModule> flowModules;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    public String getIconUri() {
        return iconUri;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    public void setFlowModules(List<FlowModule> flowModules) {
        this.flowModules = flowModules;
    }

    public List<FlowModule> getFlowModules() {
        return flowModules;
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

        if (index == null) {
            index = Integer.valueOf(0);
        }

        createdDt = new

                Date();

    }

    @PreUpdate
    @Override
    public void preUpdate() {

        updatedDt = new Date();
    }


}
