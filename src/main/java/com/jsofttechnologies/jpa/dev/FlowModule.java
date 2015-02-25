package com.jsofttechnologies.jpa.dev;


import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowComponentType;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "flow_module", uniqueConstraints = {@UniqueConstraint(columnNames = "module_name")})
@NamedQueries({@NamedQuery(name = FlowModule.FIND_ALL, query = "select fm from FlowModule fm"),
        @NamedQuery(name = FlowModule.FIND_BY_FLOW_USER_GROUP_NAME, query = "select fm from FlowModule fm, FlowUserGroupModule fum where fm.id = fum.flowModuleId and fum.groupName = :groupName order by fm.moduleTitle asc ")})
public class FlowModule implements FlowJpe {

    public static final String FIND_ALL = "FlowModule.FIND_ALL";
    public static final String FIND_BY_FLOW_USER_GROUP_NAME = "FlowModule.FIND_BY_FLOW_USER_GROUP_NAME";

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_id", nullable = false)
    private Long id;
    @Column(name = "module_name", nullable = false, unique = true, length = ColumnLengths.NAME)
    private String moduleName;
    @Column(name = "module_title", nullable = false, length = ColumnLengths.TITLE)
    private String moduleTitle;
    @Column(name = "module_icon")
    private String moduleIcon;
    @Column(name = "module_glyph", length = ColumnLengths.GLYPH)
    private String moduleGlyph;
    @Column(name = "module_task_uri", nullable = false)
    private String task;
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
    @Column(name = "style_uri")
    private String styleURI;
    @Column(name = "flow_group_name", length = ColumnLengths.NAME)
    private String flowGroupName;

    @Override
    public void setId(Object id) {

        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    public Long getId() {
        return id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getModuleIcon() {
        return moduleIcon;
    }

    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon;
    }

    public String getModuleGlyph() {
        return moduleGlyph;
    }

    public void setModuleGlyph(String moduleGlyph) {
        this.moduleGlyph = moduleGlyph;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setFlowGroupName(String flowGroupName) {
        this.flowGroupName = flowGroupName;
    }

    public String getFlowGroupName() {
        return flowGroupName;
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

    public void setStyleURI(String styleURI) {
        this.styleURI = styleURI;
    }

    public String getStyleURI() {
        return styleURI;
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

    @PostPersist
    public void postPersist() {
        this.styleURI = String.format("services/flow_style_query?compId=%d&compType=%s", this.id, FlowComponentType.MODULE.getType());
    }
}
