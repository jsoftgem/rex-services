package com.jsofttechnologies.rexwar.services.session.planner;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.rexwar.model.activity.WarActivity;
import com.jsofttechnologies.rexwar.model.activity.WarPlanner;
import com.jsofttechnologies.rexwar.model.activity.view.WarCustomerMarketView;
import com.jsofttechnologies.rexwar.services.activity.WarActivityCrudService;
import com.jsofttechnologies.rexwar.services.activity.WarPlannerCrudService;
import com.jsofttechnologies.rexwar.services.management.WarAgentQueryService;
import com.jsofttechnologies.rexwar.util.contants.Month;
import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.util.CalendarUtil;
import com.jsofttechnologies.util.ProjectHelper;
import com.jsofttechnologies.util.TableUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by Jerico on 2/16/2015.
 */
@Path("session/war/planner_service")
@Stateless
public class WarPlannerService extends FlowService {


    @EJB
    private WarPlannerCrudService warPlannerCrudService;
    @EJB
    private WarActivityCrudService warActivityCrudService;
    @EJB
    private WarAgentQueryService warAgentQueryService;

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @SkipCheck("action")
    public Response save(WarPlannerSession warPlannerSession) {
        Response response = null;

        try {

            WarPlanner planner = warPlannerSession.getWarPlanner();


            if (planner.getId() == null) {
                response = warPlannerCrudService.create(planner);
            } else {
                response = warPlannerCrudService.update(planner, planner.getId());

            }

            WarActivity[] activities = warPlannerSession.getActivities();

            if (planner.getId() == null) {
                response = warPlannerCrudService.create(planner);
            } else {
                response = warPlannerCrudService.update(planner, planner.getId());
            }

            String entity = response.getEntity().toString();

            JSONObject plannerResponse = new JSONObject(entity);

            if (response.getStatus() >= 200 && response.getStatus() < 205) {
                Long plannerId = Long.valueOf(plannerResponse.get("id").toString());
                response = warActivityCrudService.saveActivities(activities, plannerId);
            }


        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }


        return response;
    }

    @GET
    @SkipCheck("action")
    @Path("customers")
    public Response getCustomer(@QueryParam("tag") @DefaultValue("20") String tag, @QueryParam("size") @DefaultValue("25") Integer size,
                                @QueryParam("month") Month month, @QueryParam("isMonth") @DefaultValue("true") Boolean isMonth, @QueryParam("start") @DefaultValue("0") Integer start,
                                @QueryParam("schoolYear") Long schoolYear, @QueryParam("agentId") Long agentId, @QueryParam("weekStart") Long weekStart, @QueryParam("page") Integer page) {

        Response response = null;

        try {
            if (tag.equalsIgnoreCase("20")) {
                start = 0;
                size = 20;
            } else if (tag.equalsIgnoreCase("50")) {
                start = 0;
                size = 50;
            }
            int week = 0;

            if (!isMonth) {
                Date startDate = new Date(weekStart);
                week = CalendarUtil.getWeek(startDate);
            }

            String region = warAgentQueryService.getById(agentId).getRegion();

            List<WarCustomerMarketView> warCustomerMarketViewList = storedProcedures.callSchoolYearCustomer(schoolYear, agentId, isMonth, month, week, start, size, region, tag);

            boolean hasNext = false;
            boolean hasPrevious = false;
            int nextStart = 0;

            if (tag.equalsIgnoreCase("all")) {

                nextStart = start + size;

                List<WarCustomerMarketView> nextBatch = storedProcedures.callSchoolYearCustomer(schoolYear, agentId, isMonth, month, week, nextStart, size, region, tag);

                int nextBatchSize = nextBatch != null ? nextBatch.size() : 0;

                hasNext = nextBatchSize > 0;

                hasPrevious = start >= size;
                if (!hasNext) {
                    start -= size;
                }

                if (!hasPrevious) {
                    start = 0;
                }
            }


            ProjectHelper projectHelper =
                    TableUtil.createPaginationJson(size, 0, hasNext, hasPrevious, start, nextStart, page)
                            .addField("customers", new JSONArray(warCustomerMarketViewList.toArray()))
                            .addField("tag", tag)
                            .addField("month", month)
                            .addField("isMonth", isMonth)
                            .addField("week", week)
                            .addField("schoolYear", schoolYear)
                            .addField("agentId", agentId)
                            .addField("region", region);

            response = Response.ok(projectHelper.buildJsonString(), MediaType.APPLICATION_JSON_TYPE).build();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }


        return response;
    }


}
