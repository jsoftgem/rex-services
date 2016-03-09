package com.jsofttechnologies.rexwar.services.activity;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.rexwar.model.activity.WarPlanner;
import com.jsofttechnologies.rexwar.util.contants.Month;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Jerico on 1/31/2015.
 */
@Path("services/war/planner_query")
@Stateless
public class WarPlannerQueryService extends QueryService<WarPlanner> {


    public WarPlannerQueryService() {
        super(WarPlanner.class, WarPlanner.FIND_ALL);
    }

    @Path("/get_planner")
    @GET
    @SkipCheck("action")
    @Produces(MediaType.APPLICATION_JSON)
    public WarPlanner getPlanner(@QueryParam("schoolYear") Long schoolYear, @QueryParam("agent") Long agent,
                                 @QueryParam("year") Integer year,
                                 @QueryParam("month") Month month, @QueryParam("isWeek") @DefaultValue("false") Boolean isWeek) {
        try {
            setNamedQuery(WarPlanner.FIND_BY_AGENT);
            putParam("schoolYear", schoolYear);
            putParam("agent", agent);
            putParam("year", year);
            putParam("month", month);
            return getSingleResult();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), request.getHeaderNames());
        }

        return null;
    }


}
