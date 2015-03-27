package com.jsofttechnologies.rexwar.model.management;/**
 * Created by Jerico on 1/7/2015.
 */


import com.jsofttechnologies.jpa.util.ColumnLengths;
import com.jsofttechnologies.jpa.util.FlowJpe;
import com.jsofttechnologies.rexwar.model.sales.HistoricalSale;
import com.jsofttechnologies.rexwar.model.tables.*;
import com.jsofttechnologies.rexwar.util.contants.BuyingProcess;
import com.jsofttechnologies.rexwar.util.contants.Month;
import com.jsofttechnologies.rexwar.util.contants.Ownership;
import com.jsofttechnologies.rexwar.util.contants.PurchaseNature;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Jerico on 12/22/2014.
 */
@Entity
@Table(name = "war_customer")
@NamedQueries({
        @NamedQuery(name = WarCustomer.FIND_ALL, query = "select wc from WarCustomer wc"),
        @NamedQuery(name = WarCustomer.FIND_BY_CUSTOMER_CODE, query = "select wc from WarCustomer wc where wc.customerCode = :customerCode"),
        @NamedQuery(name = WarCustomer.FIND_BY_CUSTOMER_REGION, query = "select wc from WarCustomer wc where wc.regionCode = :regionCode")
})
public class WarCustomer implements FlowJpe {

    public static final String FIND_ALL = "WarCustomer.FIND_ALL";
    public static final String FIND_BY_CUSTOMER_CODE = "WarCustomer.FIND_BY_CUSTOMER_CODE";
    public static final String FIND_BY_CUSTOMER_REGION = "WarCustomer.FIND_BY_CUSTOMER_REGION";
    @Id
    @Column(name = "customer_id", nullable = false)
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
    @Column(name = "customer_code", unique = true, nullable = false)
    private String customerCode;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "war_customer_school", referencedColumnName = "war_customer_school_id", nullable = false)
    private School school;
    @Column(name = "customer_information_on_enrollment")
    private String informationOnEnrollment;
    @Column(name = "customer_diocese")
    private String diocese;
    @Column(name = "customer_congregation")
    private String congregation;
    @Column(name = "customer_association")
    private String association;
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_buying_process")
    private BuyingProcess buyingProcess;
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_nature_of_purchase", length = ColumnLengths.NAME)
    private PurchaseNature natureOfPurchase;
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_ownership", length = ColumnLengths.NAME)
    private Ownership ownership;
    @Column(name = "customer_using_journals", length = ColumnLengths.FLAG)
    private Boolean usingJournals;
    @Column(name = "customer_journal_publisher", length = ColumnLengths.TITLE)
    private String journalPublisher;
    @Column(name = "customer_using_sra", length = ColumnLengths.FLAG)
    private Boolean usingSRA;
    @Column(name = "customer_standard_outright_discount")
    private Double standardOutrightDiscount;
    @Column(name = "customer_additional_discount")
    private Double additionalDiscount;
    @Column(name = "customer_incentives")
    private Double incentives;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Contacts> contactDetails;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CustomerLevel> customerLevels;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Publisher> publisher;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<SupportGiven> supportGivens;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<HistoricalSale> historicalSales;
    @Column(name = "customer_created_by_agent_id")
    private Long createdByAgentId;
    @Column(name = "customer_owner_agent_id")
    private Long ownerAgentId;
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_cycle_evaluation")
    private Month evaluation;
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_cycle_evaluation_to")
    private Month evaluationTo;
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_cycle_ordering")
    private Month ordering;
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_cycle_ordering_to")
    private Month orderingTo;
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_cycle_delivery")
    private Month delivery;
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_cycle_delivery_to")
    private Month deliveryTo;
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_cycle_collection")
    private Month collection;
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_cycle_collection_to")
    private Month collectionTo;
    @Column(name = "customer_region_code")
    private String regionCode;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<WarCustomerMarketSchoolYear> warCustomerMarketSchoolYears;

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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Double getIncentives() {
        return incentives;
    }

    public void setIncentives(Double incentives) {
        this.incentives = incentives;
    }

    public Double getAdditionalDiscount() {
        return additionalDiscount;
    }

    public void setAdditionalDiscount(Double additionalDiscount) {
        this.additionalDiscount = additionalDiscount;
    }

    public Double getStandardOutrightDiscount() {
        return standardOutrightDiscount;
    }

    public void setStandardOutrightDiscount(Double standardOutrightDiscount) {
        this.standardOutrightDiscount = standardOutrightDiscount;
    }

    public Set<Contacts> getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(Set<Contacts> contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getInformationOnEnrollment() {
        return informationOnEnrollment;
    }

    public void setInformationOnEnrollment(String informationOnEnrollment) {
        this.informationOnEnrollment = informationOnEnrollment;
    }

    public String getDiocese() {
        return diocese;
    }

    public void setDiocese(String diocese) {
        this.diocese = diocese;
    }

    public String getCongregation() {
        return congregation;
    }

    public void setCongregation(String congregation) {
        this.congregation = congregation;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public BuyingProcess getBuyingProcess() {
        return buyingProcess;
    }

    public void setBuyingProcess(BuyingProcess buyingProcess) {
        this.buyingProcess = buyingProcess;
    }

    public PurchaseNature getNatureOfPurchase() {
        return natureOfPurchase;
    }

    public void setNatureOfPurchase(PurchaseNature natureOfPurchase) {
        this.natureOfPurchase = natureOfPurchase;
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public void setOwnership(Ownership ownership) {
        this.ownership = ownership;
    }

    public Boolean getUsingJournals() {
        return usingJournals;
    }

    public void setUsingJournals(Boolean usingJournals) {
        this.usingJournals = usingJournals;
    }

    public String getJournalPublisher() {
        return journalPublisher;
    }

    public void setJournalPublisher(String journalPublisher) {
        this.journalPublisher = journalPublisher;
    }

    public Boolean getUsingSRA() {
        return usingSRA;
    }

    public void setUsingSRA(Boolean usingSRA) {
        this.usingSRA = usingSRA;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public Set<CustomerLevel> getCustomerLevels() {
        return customerLevels;
    }

    public void setCustomerLevels(Set<CustomerLevel> customerLevels) {
        this.customerLevels = customerLevels;
    }

    public Set<Publisher> getPublisher() {
        return publisher;
    }

    public void setPublisher(Set<Publisher> publisher) {
        this.publisher = publisher;
    }

    public Set<SupportGiven> getSupportGivens() {
        return supportGivens;
    }

    public void setSupportGivens(Set<SupportGiven> supportGivens) {
        this.supportGivens = supportGivens;
    }

    public Set<HistoricalSale> getHistoricalSales() {
        return historicalSales;
    }

    public void setHistoricalSales(Set<HistoricalSale> historicalSales) {
        this.historicalSales = historicalSales;
    }

    public Long getCreatedByAgentId() {
        return createdByAgentId;
    }

    public void setCreatedByAgentId(Long createdByAgentId) {
        this.createdByAgentId = createdByAgentId;
    }

    public Long getOwnerAgentId() {
        return ownerAgentId;
    }

    public void setOwnerAgentId(Long ownerAgentId) {
        this.ownerAgentId = ownerAgentId;
    }

    public Month getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Month evaluation) {
        this.evaluation = evaluation;
    }

    public Month getEvaluationTo() {
        return evaluationTo;
    }

    public void setEvaluationTo(Month evaluationTo) {
        this.evaluationTo = evaluationTo;
    }

    public Month getOrdering() {
        return ordering;
    }

    public void setOrdering(Month ordering) {
        this.ordering = ordering;
    }

    public Month getOrderingTo() {
        return orderingTo;
    }

    public void setOrderingTo(Month orderingTo) {
        this.orderingTo = orderingTo;
    }

    public Month getDelivery() {
        return delivery;
    }

    public void setDelivery(Month delivery) {
        this.delivery = delivery;
    }

    public Month getDeliveryTo() {
        return deliveryTo;
    }

    public void setDeliveryTo(Month deliveryTo) {
        this.deliveryTo = deliveryTo;
    }

    public Month getCollection() {
        return collection;
    }

    public void setCollection(Month collection) {
        this.collection = collection;
    }

    public Month getCollectionTo() {
        return collectionTo;
    }

    public void setCollectionTo(Month collectionTo) {
        this.collectionTo = collectionTo;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Set<WarCustomerMarketSchoolYear> getWarCustomerMarketSchoolYears() {
        return warCustomerMarketSchoolYears;
    }

    public void setWarCustomerMarketSchoolYears(Set<WarCustomerMarketSchoolYear> warCustomerMarketSchoolYears) {
        this.warCustomerMarketSchoolYears = warCustomerMarketSchoolYears;
    }

    @PrePersist
    public void prePersist() {
        createdDt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDt = new Date();
    }

}
