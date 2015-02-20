package com.jsofttechnologies.rexwar.model.sales;/**
 * Created by Jerico on 1/11/2015.
 */


import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.rexwar.model.management.WarCustomer;
import com.jsofttechnologies.rexwar.model.tables.Item;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerico on 12/22/2014.
 */
@Entity
@Table(name = "war_historical_sale")
@NamedQueries({
        @NamedQuery(name = HistoricalSale.FIND_ALL, query = "select hs from HistoricalSale hs")
})
public class HistoricalSale implements FlowJpe {

    public static final String FIND_ALL = "HistoricalSale.FIND_ALL";

    @Id
    @Column(name = "war_historical_sale_id", nullable = false)
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
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "item", referencedColumnName = "item_code")
    private Item item;
    @Column(name = "slspnsn_no", length = ColumnLengths.NAME)
    private String slspsnNo;
    @Column(name = "quantity_to_ship")
    private Integer qtyToShp;
    @Column(name = "quantity_return_to_stock")
    private Integer qtyRetToStk;
    @Column(name = "net_delivery")
    private Integer netDelivery;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer", referencedColumnName = "customer_code")
    private WarCustomer customer;

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getSlspsnNo() {
        return slspsnNo;
    }

    public void setSlspsnNo(String slspsnNo) {
        this.slspsnNo = slspsnNo;
    }

    public Integer getQtyToShp() {
        return qtyToShp;
    }

    public void setQtyToShp(Integer qtyToShp) {
        this.qtyToShp = qtyToShp;
    }

    public Integer getQtyRetToStk() {
        return qtyRetToStk;
    }

    public void setQtyRetToStk(Integer qtyRetToStk) {
        this.qtyRetToStk = qtyRetToStk;
    }

    public Integer getNetDelivery() {
        return netDelivery;
    }

    public void setNetDelivery(Integer netDelivery) {
        this.netDelivery = netDelivery;
    }

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
    }

    @Override
    public void preUpdate() {
        updatedDt = new Date();
    }
}
