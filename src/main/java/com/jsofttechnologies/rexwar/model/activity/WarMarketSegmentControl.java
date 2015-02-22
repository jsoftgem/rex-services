package com.jsofttechnologies.rexwar.model.activity;

import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.rexwar.util.contants.MarketControl;
import com.jsofttechnologies.rexwar.util.contants.MarketOperator;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Jerico on 2/2/2015 18:13.
 */
@Entity
@Table(name = "war_market_segment_control")
@NamedQueries({
        @NamedQuery(name = WarMarketSegmentControl.FIND_ALL, query = "select e from WarMarketSegmentControl e")
})
public class WarMarketSegmentControl implements FlowJpe, Comparator<WarMarketSegmentControl> {

    public static final String FIND_ALL = "WarMarketSegmentControl.FIND_ALL";

    @Id
    @Column(name = "war_market_segment_control_id", nullable = false)
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
    @Column(name = "war_market_control")
    private MarketControl control;
    @Column(name = "war_market_control_operator", nullable = false)
    private MarketOperator operator;
    @Column(name = "war_market_control_order", nullable = false)
    private Integer order;
    @Column(name = "war_market_control_value", nullable = false)
    private Integer value;
    @Column(name = "war_market_control_value2")
    private Integer value2;

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

    public MarketControl getControl() {
        return control;
    }

    public void setControl(MarketControl control) {
        this.control = control;
    }

    public MarketOperator getOperator() {
        return operator;
    }

    public void setOperator(MarketOperator operator) {
        this.operator = operator;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue2() {
        return value2;
    }

    public void setValue2(Integer value2) {
        this.value2 = value2;
    }

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
    }

    @Override
    public void preUpdate() {
        updatedDt = new Date();
    }

    @Override
    public int compare(WarMarketSegmentControl o1, WarMarketSegmentControl o2) {
        return o1.getOrder().compareTo(o2.getOrder());
    }
}
