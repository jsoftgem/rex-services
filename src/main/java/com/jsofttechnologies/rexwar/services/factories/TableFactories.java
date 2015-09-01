package com.jsofttechnologies.rexwar.services.factories;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.rexwar.util.contants.*;
import com.jsofttechnologies.services.util.FlowService;

import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jerico on 1/19/2015.
 */
@Path("factories/war/table_factories")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class TableFactories extends FlowService {

    @Path("/relationship_types")
    @GET
    @SkipCheck("action")
    public RelationType[] relationTypes() {
        return RelationType.values();
    }

    @Path("/education_levels")
    @GET
    @SkipCheck("action")
    public EducationLevel[] educationLevels() {
        return EducationLevel.values();
    }


    @Path("/contact_types")
    @GET
    @SkipCheck("action")
    public ContactType[] contactTypes() {
        return ContactType.values();
    }

    @Path("/months")
    @GET
    @SkipCheck("action")
    public Month[] months() {
        return Month.values();
    }

    @Path("/weeks")
    @GET
    @SkipCheck("action")
    public Integer[] weeks() {
        return new Integer[]{1, 2, 3, 4, 5};
    }

    @Path("/years")
    @GET
    @SkipCheck("authorization")
    public Integer[] years() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int to = calendar.get(Calendar.YEAR) + 18;
        int from = to - 68;
        int size = to - from;
        Integer[] years = new Integer[size];

        int index = 0;
        for (int i = from; i < to; i++) {
            years[index] = i;
            index++;
        }

        return years;
    }

    @Path("/market_segments")
    @GET
    @SkipCheck("action")
    public MarketSegment[] marketSegments() {
        return MarketSegment.values();
    }

    @Path("/market_operators")
    @GET
    @SkipCheck("action")
    public MarketOperator[] marketOperators() {
        return MarketOperator.values();
    }

    @Path("/activity_types")
    @GET
    @SkipCheck("action")
    public ActivityType[] activityTypes() {
        List<ActivityType> activityTypeList = Arrays.asList(ActivityType.values());
        activityTypeList.remove(ActivityType.SCHOOL);
        return activityTypeList.toArray(new ActivityType[activityTypeList.size()]);
    }

    @Path("/market_controls")
    @GET
    @SkipCheck("authorization")
    public MarketControl[] marketControls() {
        return MarketControl.values();
    }


}
