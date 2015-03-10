package com.jsofttechnologies.rexwar.services.report;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.model.reports.WarReportMonthlyCustomerView;
import com.jsofttechnologies.rexwar.services.management.WarAgentQueryService;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.rexwar.util.contants.Month;
import com.jsofttechnologies.services.util.FlowPermissionService;
import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;
import com.jsofttechnologies.util.TableUtil;

import javax.ejb.EJB;
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
 * Created by Jerico on 2/23/2015.
 */
@Path("services/war/report_monthly_service")
@Stateless
public class WarReportMonthlyService extends FlowService {

    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

    @EJB
    private FlowPermissionService flowPermissionService;
    @EJB
    private WarAgentQueryService warAgentQueryService;


    @GET
    @SkipCheck("action")
    @Path("customers")
    public Response findWarReportMonthlyCustomer(
            @QueryParam("isYear") @DefaultValue("false") Boolean isYear,
            @QueryParam("isMonth") @DefaultValue("false") Boolean isMonth,
            @QueryParam("isAgent") @DefaultValue("false") Boolean isAgent,
            @QueryParam("isRegion") @DefaultValue("false") Boolean isRegion,
            @QueryParam("isCustomer") @DefaultValue("false") Boolean isCustomer,
            @QueryParam("schoolYear") Long schoolYear, @QueryParam("month") Month month, @QueryParam("agentId") Long agentId,
            @QueryParam("region") String region, @QueryParam("size") @DefaultValue("25") Integer size, @QueryParam("customerId") Long customerId,
            @QueryParam("start") @DefaultValue("0") Integer start, @QueryParam("tag") @DefaultValue("20") String tag) {
        Response response = null;


        if (flowPermissionService.hasProfileEJB(WarConstants.AGENT_PROFILE)) {
            isAgent = Boolean.TRUE;
            isRegion = Boolean.TRUE;
            FlowSessionHelper.Promise session = getUserSession();
            WarAgent warAgent = warAgentQueryService.findAgentByUsername(session.getFlowUser().getUsername());
            agentId = warAgent.getId();
            region = warAgent.getRegion();
        }

        List<WarReportMonthlyCustomerView> warReportMonthlyCustomerViewList = new ArrayList<>();

        boolean complete = isYear && isMonth && isAgent && isRegion && isCustomer;

        String query = "select * from " + WarConstants.VIEW_WAR_REPORT_MONTHLY_CUSTOMER + " w";

        try {
            if (!complete) {
                int count = 0;

                if (isYear) {
                    query += " where w.report_school_year =" + schoolYear;
                    count++;
                }

                if (isMonth) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "w.report_month = '" + month.toString() + "'";
                }

                if (isAgent) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "w.report_agent = " + agentId;
                }

                if (isRegion) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "lower(w.report_region) like '" + region.toLowerCase() + "'";
                }

                if (isCustomer) {
                    if (count > 0) {
                        query += " and ";
                    } else {
                        query += " where ";
                        count++;
                    }
                    query += "w.report_customer_id =" + customerId;
                }
            } else {
                query += " where w.report_school_year =" + schoolYear + " and w.report_month = '" + month.toString() + "' and w.report_agent_id = " + agentId + " and lower(w.report_region) ='" + region.toLowerCase() + "' and w.report_customer_id = " + customerId;
            }


            switch (tag) {
                case "All":
                case "all":
                    query += " order by w.report_customer asc";
                    break;
                case "20":
                    query += " order by w.report_tag_index desc";
                    break;
                case "50":
                    query += " order by w.report_tag_index desc";
                    break;
            }

            warReportMonthlyCustomerViewList = entityManager.createNativeQuery(query, WarReportMonthlyCustomerView.class)
                    .getResultList();


            int length = start + warReportMonthlyCustomerViewList.size();


         /*   List<WarReportMonthlyCustomerView> nextBatch = entityManager.createNativeQuery(query, WarReportMonthlyCustomerView.class)
                    .setFirstResult(length)
                    .setMaxResults(size)
                    .getResultList();
*/
            boolean hasNext = false;

            boolean hasPrevious = start > size;

            ProjectHelper projectHelper = TableUtil.createPaginationJson(size, start, hasNext, hasPrevious, start, length, 0)
                    .addField("tag", tag)
                    .addField("monthlyReports", warReportMonthlyCustomerViewList.toArray())
                    .addField("isAgent", isAgent)
                    .addField("isYear", isYear)
                    .addField("isMonth", isMonth)
                    .addField("isRegion", isRegion)
                    .addField("isCustomer", isCustomer)
                    .addField("schoolYear", schoolYear)
                    .addField("month", month != null ? month.toString() : null)
                    .addField("region", region)
                    .addField("agentId", agentId)
                    .addField("customerId", customerId)
                    .addField("length", length);


            response = Response.ok(projectHelper.buildJsonString(), MediaType.APPLICATION_JSON_TYPE).build();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }
        return response;
    }
}
