package com.jsofttechnologies.rexwar.services.report;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.rexwar.model.reports.WarReportWeeklyAgentView;
import com.jsofttechnologies.rexwar.model.reports.WarReportWeeklyAgentViewCustomer;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.rexwar.util.contants.Month;
import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.util.ProjectConstants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerico on 2/6/2015.
 */
@Path("services/war/report_weekly_service")
@Stateless
public class WarReportWeeklyService extends FlowService {

    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

    @Path("agents")
    @GET
    @SkipCheck("action")
    public Response findWarReportWeeklyAgents(
            @QueryParam("isYear") @DefaultValue("false") Boolean isYear,
            @QueryParam("isMonth") @DefaultValue("false") Boolean isMonth,
            @QueryParam("isAgent") @DefaultValue("false") Boolean isAgent,
            @QueryParam("year") Integer year, @QueryParam("month") Month month, @QueryParam("agent") Long agent,
            @QueryParam("size") Integer max, @QueryParam("resultCount") Integer resultCount, @QueryParam("firstResult") Integer firstResult
    ) {
        Response response = null;

        List<WarReportWeeklyAgentView> warReportWeeklyAgentViewList = new ArrayList<>();

        boolean complete = isYear && isMonth && isAgent;

        String query = "select * from " + WarConstants.VIEW_WAR_REPORT_WEEKLY_AGENT + " w";

        try {
            if (!complete) {
                if (isYear && isMonth) {
                    query += " where w.report_year =" + year + " and w.report_month = '" + month.toString() + "'";
                } else if (isYear && isAgent) {
                    query += " where w.report_year = " + year + " and w.report_agent_id = " + agent;
                } else if (isMonth && isAgent) {
                    query += " where w.report_month = '" + month.toString() + "' and w.report_agent_id = " + agent;
                } else if (isYear) {
                    query += " where w.report_year =" + year;
                } else if (isMonth) {
                    query += " where w.report_month = '" + month.toString() + "'";
                } else if (isAgent) {
                    query += " where w.report_agent_id = " + agent;
                }
            } else {
                query += " where w.report_year =" + year + " and w.report_month = '" + month.toString() + "' and w.report_agent_id = " + agent;
            }


            query += " order by w.report_date asc";

            warReportWeeklyAgentViewList = entityManager.createNativeQuery(query, WarReportWeeklyAgentView.class).getResultList();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        response = Response.ok(warReportWeeklyAgentViewList, MediaType.APPLICATION_JSON_TYPE).build();

        return response;
    }


    @Path("agent_customer")
    @GET
    @SkipCheck("action")
    public Response findWarReportWeeklyAgentsCustomer(@QueryParam("month") Month month, @QueryParam("agent") Long agent, @QueryParam("year") Integer year, @QueryParam("week") Integer week) {

        Response response = null;
        List<WarReportWeeklyAgentViewCustomer> viewCustomers = new ArrayList<>();

        try {
            String query = "select * from " + WarConstants.VIEW_WAR_REPORT_WEEKLY_AGENT_CUSTOMER_VIEW + " w";
            int count = 0;

            if (month != null) {
                query += " where w.report_month = '" + month.toString() + "' ";
                count++;
            }

            if (agent != null) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += " w.report_agent = " + agent;
            }

            if (year != null) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += " w.report_year = " + year;
            }

            if (week != null) {
                if (count > 0) {
                    query += " and ";
                } else {
                    query += " where ";
                    count++;
                }
                query += " w.report_week = " + week;
            }

            query += " order by w.report_date asc";

            viewCustomers = entityManager.createNativeQuery(query, WarReportWeeklyAgentViewCustomer.class).getResultList();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        response = Response.ok(viewCustomers, MediaType.APPLICATION_JSON_TYPE).build();

        return response;
    }


}
