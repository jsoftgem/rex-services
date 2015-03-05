package com.jsofttechnologies.jpa.admin;/**
 * Created by Jerico on 1/2/2015.
 */

import com.jsofttechnologies.jpa.util.AppLayout;
import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 12/22/2014.
 */
@Entity
@Table(name = "flow_user_app_setting")
@NamedQueries({
        @NamedQuery(name = FlowUserAppSetting.FIND_ALL, query = "select fuas from FlowUserAppSetting fuas"),
        @NamedQuery(name = FlowUserAppSetting.FIND_BY_USER, query = "select fuas from FlowUserAppSetting fuas where fuas.userId=:userId")
})
public class FlowUserAppSetting implements FlowJpe {

    public static final String FIND_ALL = "FlowUserAppSetting.FIND_ALL";
    public static final String FIND_BY_USER = "FlowUserAppSetting.FIND_BY_USER";

    @Id
    @Column(name = "flow_id", nullable = false)
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
    @Column(name = "flow_user_id", nullable = false)
    private Long userId;
    @Column(name = "fluid_style", nullable = false, length = ColumnLengths.TITLE)
    private String style;
    @Column(name = "fluid_theme", nullable = false, length = ColumnLengths.TITLE)
    private String theme;
    @Column(name = "fluid_bg_color", nullable = false, length = ColumnLengths.TITLE)
    private String bgColor;
    @Column(name = "fluid_menu", nullable = false, length = ColumnLengths.TITLE)
    private String menu;
    @Column(name = "fluid_hide_menu", length = ColumnLengths.FLAG)
    private Boolean hideMenu;
    @Column(name = "fluid_layout", nullable = false)
    @Enumerated(EnumType.STRING)
    private AppLayout layout;


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

    @PrePersist
    public void prePersist() {
        layout = AppLayout.FLUIDSCREEN;
        createdDt = new Date();
    }

    @PreUpdate
    public void preUpdate() {

        if (layout == null) {
            layout = AppLayout.FLUIDSCREEN;
        }
        updatedDt = new Date();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Boolean getHideMenu() {
        return hideMenu;
    }

    public void setHideMenu(Boolean hideMenu) {
        this.hideMenu = hideMenu;
    }

    public AppLayout getLayout() {
        return layout;
    }

    public void setLayout(AppLayout layout) {
        this.layout = layout;
    }
}
