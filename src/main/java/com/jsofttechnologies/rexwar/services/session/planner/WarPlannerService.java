package com.jsofttechnologies.rexwar.services.session.planner;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.rexwar.model.activty.WarActivity;
import com.jsofttechnologies.rexwar.model.activty.WarPlanner;
import com.jsofttechnologies.rexwar.services.activity.WarActivityCrudService;
import com.jsofttechnologies.rexwar.services.activity.WarPlannerCrudService;
import com.jsofttechnologies.services.util.FlowService;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

}
