package com.jsofttechnologies.rexwar.model.activity.view;

import com.jsofttechnologies.rexwar.util.contants.Month;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Jerico on 2/20/2015.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = WarCustomerMarketView.PROCEDURE_SCHOOL_YEAR_CUSTOMER,
                procedureName = WarCustomerMarketView.PROCEDURE_SCHOOL_YEAR_CUSTOMER,
                resultClasses = {WarCustomerMarketView.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarCustomerMarketView.IN_SCHOOL_YEAR, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarCustomerMarketView.IN_AGENT_ID, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarCustomerMarketView.IN_IS_MONTH, type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarCustomerMarketView.IN_MONTH, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarCustomerMarketView.IN_WEEK, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarCustomerMarketView.IN_SIZE, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarCustomerMarketView.IN_START_AT, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarCustomerMarketView.IN_REGION, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = WarCustomerMarketView.IN_TAG, type = String.class)
                }
        )
})
@Entity
@XmlRootElement
public class WarCustomerMarketView implements Serializable {

    public static final String PROCEDURE_SCHOOL_YEAR_CUSTOMER = "school_year_customer";
    public static final String IN_SCHOOL_YEAR = "school_year";
    public static final String IN_AGENT_ID = "agent_id";
    public static final String IN_IS_MONTH = "isMonth";
    public static final String IN_MONTH = "plannedMonth";
    public static final String IN_WEEK = "week";
    public static final String IN_SIZE = "size";
    public static final String IN_START_AT = "startAt";
    public static final String IN_REGION = "region";
    public static final String IN_TAG = "tag";
    @Id
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_market_potential_segment")
    private String marketSegment;
    @Column(name = "customer_market_potential")
    private Integer marketPotential;
    @Column(name = "customer_school_year")
    private Long schoolYear;
    @Column(name = "customer_material_advisor")
    private Long materialAdvisor;
    @Column(name = "customer_frequency")
    private Integer frequency;
    @Column(name = "customer_month")
    @Enumerated(EnumType.STRING)
    private Month month;
    @Column(name = "customer_year")
    private Integer year;
    @Column(name = "customer_week")
    private Integer week;
    @Column(name = "customer_index")
    private Integer index;
    @Column(name = "customer_region")
    private String region;

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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "WarCustomerMarketView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marketSegment='" + marketSegment + '\'' +
                ", marketPotential=" + marketPotential +
                ", schoolYear=" + schoolYear +
                ", materialAdvisor=" + materialAdvisor +
                ", frequency=" + frequency +
                ", month=" + month +
                ", year=" + year +
                ", week=" + week +
                '}';
    }

    public String toJson() {
        return "{" +
                "\"id\":" + id +
                ",\"name=\":" + name + '\'' +
                ",\" marketSegment\":" + marketSegment + '\'' +
                ",\" marketPotential\":" + marketPotential +
                ",\"schoolYear\":" + schoolYear +
                ",\"materialAdvisor\":" + materialAdvisor +
                ",\"frequency\":" + frequency +
                ",\"month\":" + month +
                ",\"year\":" + year +
                ",\"week\":" + week +
                "}";
    }
}
