package com.jsofttechnologies.rexwar.model.activty.view;

import com.jsofttechnologies.rexwar.util.contants.Month;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Jerico on 2/20/2015.
 */
@Entity
@XmlRootElement
@IdClass(WarCustomerMarketViewID.class)
public class WarCustomerMarketView implements Serializable {
    @Id
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_market_potential_segment")
    private String marketSegment;
    @Column(name = "customer_market_potential")
    private Integer marketPotential;
    @Id
    @Column(name = "customer_school_year")
    private Long schoolYear;
    @Column(name = "customer_material_advisor")
    private Long materialAdvisor;
    @Column(name = "customer_frequency")
    private Integer frequency;
    @Column(name = "customer_month")
    private Month month;
    @Column(name = "customer_year")
    private Integer year;
    @Column(name = "customer_week")
    private Integer week;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarketSegment() {
        return marketSegment;
    }

    public void setMarketSegment(String marketSegment) {
        this.marketSegment = marketSegment;
    }

    public Integer getMarketPotential() {
        return marketPotential;
    }

    public void setMarketPotential(Integer marketPotential) {
        this.marketPotential = marketPotential;
    }

    public Long getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Long schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Long getMaterialAdvisor() {
        return materialAdvisor;
    }

    public void setMaterialAdvisor(Long materialAdvisor) {
        this.materialAdvisor = materialAdvisor;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}
