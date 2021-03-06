package com.jsofttechnologies.rexwar.model.reports;

import com.jsofttechnologies.rexwar.util.contants.Month;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Jerico on 2/23/2015.
 */
@Entity
@XmlRootElement
@IdClass(WarReportMonthlyCustomerViewID.class)
public class WarReportMonthlyCustomerView implements Serializable {
    @Id
    @Column(name = "report_customer_id", nullable = false)
    private Long customerId;
    @Column(name = "report_customer", nullable = false)
    private String customerName;
    @Id
    @Column(name = "report_month")
    @Enumerated(EnumType.STRING)
    private Month month;
    @Column(name = "report_agent")
    private Long agent;
    @Column(name = "report_materials_advisor")
    private String materialsAdvisor;
    @Column(name = "report_school_year")
    private Long schoolYear;
    @Column(name = "report_year")
    private Integer year;
    @Column(name = "report_region")
    private String region;
    @Column(name = "report_frequency")
    private Integer customerFrequency;
    @Column(name = "report_planned")
    private Integer planned;
    @Column(name = "report_exam_copies_distribution")
    private Integer ecd;
    @Column(name = "report_invitation_to_events")
    private Integer ite;
    @Column(name = "report_confirmation_of_events")
    private Integer coe;
    @Column(name = "report_follow_up_payment")
    private Integer fp;
    @Column(name = "report_giveaways_distribution")
    private Integer gd;
    @Column(name = "report_delivery_of_incentive_donation")
    private Integer doi;
    @Column(name = "report_purchase_order")
    private Integer po;
    @Column(name = "report_delivery_of_additional_order_trm_compliance")
    private Integer daotrc;
    @Column(name = "report_book_list")
    private Integer bookList;
    @Column(name = "report_updated_customer_info_sheet")
    private Integer ucis;
    @Column(name = "report_implemented_ex_sem")
    private Integer ies;
    @Column(name = "report_tag_index")
    private Integer index;
    @Column(name = "report_customer_specific_activity")
    private Integer customerSpecificActivity;
    @Column(name = "report_bootcamp")
    private Integer bootcamp;
    @Column(name = "report_aecon")
    private Integer aecon;
    @Column(name = "report_ceap")
    private Integer ceap;
    @Column(name = "report_collection_and_pr")
    private Integer collectionAndPr;
    @Column(name = "report_worked_with")
    private Integer workedWith;
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Long getAgent() {
        return agent;
    }

    public void setAgent(Long agent) {
        this.agent = agent;
    }

    public String getMaterialsAdvisor() {
        return materialsAdvisor;
    }

    public void setMaterialsAdvisor(String materialsAdvisor) {
        this.materialsAdvisor = materialsAdvisor;
    }

    public Long getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Long schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getCustomerFrequency() {
        return customerFrequency;
    }

    public void setCustomerFrequency(Integer customerFrequency) {
        this.customerFrequency = customerFrequency;
    }

    public Integer getPlanned() {
        return planned;
    }

    public void setPlanned(Integer planned) {
        this.planned = planned;
    }

    public Integer getEcd() {
        return ecd;
    }

    public void setEcd(Integer ecd) {
        this.ecd = ecd;
    }

    public Integer getIte() {
        return ite;
    }

    public void setIte(Integer ite) {
        this.ite = ite;
    }

    public Integer getCoe() {
        return coe;
    }

    public void setCoe(Integer coe) {
        this.coe = coe;
    }

    public Integer getFp() {
        return fp;
    }

    public void setFp(Integer fp) {
        this.fp = fp;
    }

    public Integer getGd() {
        return gd;
    }

    public void setGd(Integer gd) {
        this.gd = gd;
    }

    public Integer getDoi() {
        return doi;
    }

    public void setDoi(Integer doi) {
        this.doi = doi;
    }

    public Integer getPo() {
        return po;
    }

    public void setPo(Integer po) {
        this.po = po;
    }

    public Integer getDaotrc() {
        return daotrc;
    }

    public void setDaotrc(Integer daotrc) {
        this.daotrc = daotrc;
    }

    public Integer getBookList() {
        return bookList;
    }

    public void setBookList(Integer bookList) {
        this.bookList = bookList;
    }

    public Integer getUcis() {
        return ucis;
    }

    public void setUcis(Integer ucis) {
        this.ucis = ucis;
    }

    public Integer getIes() {
        return ies;
    }

    public void setIes(Integer ies) {
        this.ies = ies;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getCustomerSpecificActivity() {
        return customerSpecificActivity;
    }

    public void setCustomerSpecificActivity(Integer customerSpecificActivity) {
        this.customerSpecificActivity = customerSpecificActivity;
    }

    public Integer getBootcamp() {
        return bootcamp;
    }

    public void setBootcamp(Integer bootcamp) {
        this.bootcamp = bootcamp;
    }

    public Integer getAecon() {
        return aecon;
    }

    public void setAecon(Integer aecon) {
        this.aecon = aecon;
    }

    public Integer getCeap() {
        return ceap;
    }

    public void setCeap(Integer ceap) {
        this.ceap = ceap;
    }

    public Integer getCollectionAndPr() {
        return collectionAndPr;
    }

    public void setCollectionAndPr(Integer collectionAndPr) {
        this.collectionAndPr = collectionAndPr;
    }

    public Integer getWorkedWith() {
        return workedWith;
    }

    public void setWorkedWith(Integer workedWith) {
        this.workedWith = workedWith;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarReportMonthlyCustomerView)) return false;

        WarReportMonthlyCustomerView that = (WarReportMonthlyCustomerView) o;

        if (!getCustomerId().equals(that.getCustomerId())) return false;
        return getAgent().equals(that.getAgent());

    }

    @Override
    public int hashCode() {
        int result = getCustomerId().hashCode();
        result = 31 * result + getAgent().hashCode();
        return result;
    }
}
