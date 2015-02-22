package com.jsofttechnologies.rexwar.services.activity;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.rexwar.model.activity.WarActivity;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jerico on 1/30/2015.
 */
@Path("services/war/activity_query")
@Stateless
public class WarActivityQueryService extends QueryService<WarActivity> {

    public WarActivityQueryService() {
        super(WarActivity.class, WarActivity.FIND_ALL);
    }


    @Path("/day_activities")
    @GET
    @SkipCheck("authorization")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WarActivity> getActivityDay(@QueryParam("schoolYear") Long schoolYear, @QueryParam("agent") Long agent,
                                            @QueryParam("date") Date date) {
        List<WarActivity> activities = new ArrayList<>();

        if (schoolYear != null) {
            setNamedQuery(WarActivity.FIND_BY_EVENT_SCHOOL_YEAR_DATE);
            if (agent != null) {
                setNamedQuery(WarActivity.FIND_BY_EVENT_SCHOOL_YEAR_AGENT_DATE);
                putParam("agent", agent);
            }
            putParam("schoolYear", schoolYear);
            putParam("date",date);

            activities = doGetResultList();
        }

        return activities;
    }

    @Path("/events")
    @GET
    @SkipCheck("authorization")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WarActivity> getActivity(@QueryParam("schoolYear") Long schoolYear, @QueryParam("agent") Long agent,
                                         @QueryParam("start") String start, @QueryParam("end") String end, @QueryParam("_") Long _) {
        List<WarActivity> activities = new ArrayList<>();

        if (schoolYear != null) {
            setNamedQuery(WarActivity.FIND_BY_EVENT_SCHOOL_YEAR);
            if (agent != null) {
                setNamedQuery(WarActivity.FIND_BY_EVENT_SCHOOL_YEAR_AGENT);
                putParam("agent", agent);
            }

            putParam("schoolYear", schoolYear);
            String[] splitStart = start.split("-");
            String[] splitEnd = end.split("-");

            Calendar startCal = Calendar.getInstance();
            startCal.set(Calendar.YEAR, Integer.valueOf(splitStart[0]));
            startCal.set(Calendar.MONTH, Integer.valueOf(splitStart[1])-1);
            startCal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(splitStart[2]));

            Calendar endCal = Calendar.getInstance();
            endCal.set(Calendar.YEAR, Integer.valueOf(splitEnd[0]));
            endCal.set(Calendar.MONTH, Integer.valueOf(splitEnd[1])-1);
            endCal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(splitEnd[2]));

            Date startDt = startCal.getTime();
            Date endDt = endCal.getTime();

            putParam("start", startDt);
            putParam("end", endDt);

            activities = doGetResultList();
        }

        return activities;
    }

}
