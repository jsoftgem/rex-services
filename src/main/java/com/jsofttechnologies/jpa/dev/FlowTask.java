package com.jsofttechnologies.jpa.dev;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowComponentType;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "flow_task", uniqueConstraints = {@UniqueConstraint(columnNames = "flow_name")})
@NamedQueries({@NamedQuery(name = FlowTask.FIND_ALL, query = "select ft from FlowTask ft"),
        @NamedQuery(name = FlowTask.FIND_BY_ID, query = "select ft from FlowTask ft where ft.id=:id"),
        @NamedQuery(name = FlowTask.FIND_BY_NAME, query = "select ft from FlowTask  ft where ft.name=:name")})
public class FlowTask implements FlowJpe {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "FlowTask.FIND_ALL";
    public static final String FIND_BY_ID = "FlowTask.FIND_BY_ID";
    public static final String FIND_BY_NAME = "FlowTask.FIND_BY_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_id", nullable = false)
    private Long id;

    @Column(name = "flow_name", nullable = false, unique = true, length = ColumnLengths.NAME)
    private String name;

    @Column(name = "flow_title", nullable = false, length = ColumnLengths.TITLE)
    private String title;

    @Column(name = "flow_glyph", nullable = false, length = ColumnLengths.GLYPH)
    private String glyph;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<FlowPage> pages;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDt;
    @JsonIgnore
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDt;
    @JsonIgnore
    @Column(name = "usage_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDt;
    @JsonIgnore
    @Column(name = "usage_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDt;
    @JsonIgnore
    @Column(name = "description")
    private String description;


    @Column(name = "default_size", length = 2, insertable = false, updatable = false)
    private Integer size;

    @Column(name = "default_active", length = 1, insertable = false, updatable = false)
    private Boolean active;

    @Column(name = "default_locked", length = 1, insertable = false, updatable = false)
    private Boolean locked;

    @Column(name = "default_pinned", length = 1, insertable = false, updatable = false)
    private Boolean pinned;

    @Column(name = "default_show_tool_bar", length = 1, insertable = false, updatable = false)
    private Boolean showToolBar;

    @Column(name = "defalt_page", insertable = false, updatable = false)
    private FlowPage page;

    @Column(name = "default_page_param", insertable = false, updatable = false)
    private String pageParam;

    @Column(name = "style_uri")
    private String styleURI;

    @Column(name = "default_flow_id")
    private String flowId;
    @Column(name = "flow_task_lazyload", length = ColumnLengths.FLAG)
    private Boolean lazyLoad;
    @Column(name = "flow_task_module_js", length = ColumnLengths.NAME)
    private String moduleJS;
    @Column(name = "flow_task_module_files", columnDefinition = "TEXT")
    private String moduleFiles;

    @Override
    public void setId(Object id) {
        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    @Override
    public Long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGlyph() {
        return glyph;
    }

    public void setGlyph(String glyph) {
        this.glyph = glyph;
    }

    /**
     * @return the pages
     */
    public Set<FlowPage> getPages() {
        return pages;
    }

    /**
     * @param pages the pages to set
     */
    public void setPages(Set<FlowPage> pages) {
        this.pages = pages;
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

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setPinned(Boolean clipped) {
        this.pinned = clipped;
    }

    public Boolean getPinned() {
        return pinned;
    }

    public void setShowToolBar(Boolean showToolBar) {
        this.showToolBar = showToolBar;
    }

    public Boolean getShowToolBar() {
        return showToolBar;
    }

    public void setStyleURI(String styleURI) {
        this.styleURI = styleURI;
    }

    public String getStyleURI() {
        return styleURI;
    }

    public void setPage(FlowPage page) {
        this.page = page;
    }

    public FlowPage getPage() {
        return page;
    }

    public void setPageParam(String pageParam) {
        this.pageParam = pageParam;
    }

    public String getPageParam() {
        return pageParam;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowId() {
        return flowId;
    }

    public Boolean getLazyLoad() {
        return lazyLoad;
    }

    public void setLazyLoad(Boolean lazyLoad) {
        this.lazyLoad = lazyLoad;
    }

    public String getModuleJS() {
        return moduleJS;
    }

    public void setModuleJS(String moduleJS) {
        this.moduleJS = moduleJS;
    }

    public String getModuleFiles() {
        return moduleFiles;
    }

    public void setModuleFiles(String moduleFiles) {
        this.moduleFiles = moduleFiles;
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
        this.styleURI = String.format("services/flow_style_query?compId=%d&compType=%s", this.id, FlowComponentType.TASK.getType());
    }


}
