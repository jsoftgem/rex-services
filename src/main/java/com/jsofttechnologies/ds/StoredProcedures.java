package com.jsofttechnologies.ds;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.rexwar.model.activity.view.WarCustomerMarketView;
import com.jsofttechnologies.rexwar.model.reports.WarAgentCustomerSummary;
import com.jsofttechnologies.rexwar.util.contants.Month;
import com.jsofttechnologies.util.ProjectConstants;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerico on 2/22/2015.
 */
@Stateless
public class StoredProcedures {

    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;
    @EJB
    private MergeExceptionSummary exceptionSummary;

    private static final String SCHOOL_YEAR_CUSTOMER = "school_year_customer";

    public List<WarCustomerMarketView> callSchoolYearCustomer(Long schoolYear, Long agentId, boolean isMonth, Month month, Integer week,
                                                              Integer start, Integer size, String region, String tag) {

        List<WarCustomerMarketView> warCustomerMarketViewList = new ArrayList<>();

        try {

            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(WarCustomerMarketView.PROCEDURE_SCHOOL_YEAR_CUSTOMER);
            query.setParameter(WarCustomerMarketView.IN_SCHOOL_YEAR, schoolYear);
            query.setParameter(WarCustomerMarketView.IN_AGENT_ID, agentId);
            query.setParameter(WarCustomerMarketView.IN_IS_MONTH, isMonth);
            query.setParameter(WarCustomerMarketView.IN_MONTH, month.toString());
            query.setParameter(WarCustomerMarketView.IN_WEEK, week);
            query.setParameter(WarCustomerMarketView.IN_SIZE, size);
            query.setParameter(WarCustomerMarketView.IN_START_AT, start);
            query.setParameter(WarCustomerMarketView.IN_REGION, region);
            query.setParameter(WarCustomerMarketView.IN_TAG, tag.toLowerCase());
            warCustomerMarketViewList = query.getResultList();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        return warCustomerMarketViewList;
    }


    public List<WarAgentCustomerSummary> callAgentCustomerSummary(Long customerId, Long schoolYearId) {

        List<WarAgentCustomerSummary> warAgentCustomerSummaryList = new ArrayList<>();

        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(WarAgentCustomerSummary.PROCEDURE_AGENT_CUSTOMER_SUMMARY);
            query.setParameter(WarAgentCustomerSummary.IN_CUSTOMER_ID, customerId);
            query.setParameter(WarAgentCustomerSummary.IN_SCHOOL_YEAR_ID, schoolYearId);
            warAgentCustomerSummaryList = query.getResultList();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        return warAgentCustomerSummaryList;
    }

}
