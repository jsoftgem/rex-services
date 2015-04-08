package com.jsofttechnologies.jpa.dev;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowComponentType;
import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.report.utlil.ReportColumn;
import com.jsofttechnologies.report.utlil.ReportHeader;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "FlowPage")
@Table(name = "flow_page", uniqueConstraints = {@UniqueConstraint(columnNames = {"page_name"})})
@NamedQueries({@NamedQuery(name = FlowPage.FIND_ALL, query = "select fp from FlowPage fp"), @NamedQuery(name = FlowPage.FIND_BY_NAME,
        query = "select fp from FlowPage fp where fp.name=:name ")})
@ReportHeader(name = "Flow Page")
public class FlowPage implements FlowJpe {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "FlowPage.FIND_ALL";
    public static final String FIND_BY_NAME = "FlowPage.FIND_BY_NAME";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    private Long id;
    @ReportColumn(name = "Home URL")
    @Column(name = "home_url", nullable = false)
    private String home;
    @ReportColumn(name = "Name")
    @Column(name = "page_name", nullable = false, unique = true, length = ColumnLengths.NAME)
    private String name;
    @ReportColumn(name = "Title")
    @Column(name = "page_title", nullable = false, length = ColumnLengths.TITLE)
    private String title;
    @Column(name = "get_uri")
    private String get;
    @Column(name = "put_uri")
    private String put;
    @Column(name = "post_uri")
    private String post;
    @Column(name = "delete_uri")
    private String delURL;
    @Column(name = "auto_get_loader")
    private Boolean autoGet;
    @Column(name = "page_is_home", nullable = false, length = ColumnLengths.FLAG)
    private Boolean isHome;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDt;
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
    @Column(name = "style_uri")
    private String styleURI;
    @Column(name = "page_link_enabled")
    private Boolean pageLinkEnabled;
    @Column(name = "get_param")
    private String getParam;
    @Column(name = "put_param")
    private String putParam;
    @Column(name = "post_param")
    private String postParam;
    @Column(name = "delete_param")
    private String deleteParam;

    @Override
    public void setId(Object id) {

        this.id = id != null ? Long.valueOf(id.toString()) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
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

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public String getPut() {
        return put;
    }

    public void setPut(String put) {
        this.put = put;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDelURL() {
        return delURL;
    }

    public void setDelURL(String delURL) {
        this.delURL = delURL;
    }

    public void setAutoGet(Boolean autoGet) {
        this.autoGet = autoGet;
    }

    public Boolean getAutoGet() {
        return autoGet;
    }

    public void setIsHome(Boolean isHome) {
        this.isHome = isHome;
    }

    public Boolean getIsHome() {
        return isHome;
    }

    public Boolean getPageLinkEnabled() {
        return pageLinkEnabled;
    }

    public void setPageLinkEnabled(Boolean pageLinkEnabled) {
        this.pageLinkEnabled = pageLinkEnabled;
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

        if (isHome == null) {
            isHome = Boolean.FALSE;
        }

        if (autoGet == null) {
            autoGet = Boolean.FALSE;
        }
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
        if (!(o instanceof FlowPage)) return false;

        FlowPage flowPage = (FlowPage) o;

        if (!name.equals(flowPage.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @PostPersist
    public void postPersist() {
        this.styleURI = String.format("services/flow_style_query?compId=%d&compType=%s", this.id, FlowComponentType.PAGE.getType());
    }

    public String getGetParam() {
        return getParam;
    }

    public void setGetParam(String getParam) {
        this.getParam = getParam;
    }

    public String getPutParam() {
        return putParam;
    }

    public void setPutParam(String putParam) {
        this.putParam = putParam;
    }

    public String getPostParam() {
        return postParam;
    }

    public void setPostParam(String postParam) {
        this.postParam = postParam;
    }

    public String getDeleteParam() {
        return deleteParam;
    }

    public void setDeleteParam(String deleteParam) {
        this.deleteParam = deleteParam;
    }
}
