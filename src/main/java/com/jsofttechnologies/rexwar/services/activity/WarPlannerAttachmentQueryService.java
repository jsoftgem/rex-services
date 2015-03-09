package com.jsofttechnologies.rexwar.services.activity;

import com.jsofttechnologies.rexwar.model.activity.WarPlannerAttachment;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Jerico on 3/8/2015.
 */
@Path("services/war/planner_attachment_query")
@Stateless
public class WarPlannerAttachmentQueryService extends QueryService<WarPlannerAttachment> {

    public WarPlannerAttachmentQueryService() {
        super(WarPlannerAttachment.class);
    }

    @POST
    @Path("attachment_by_school_year")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WarPlannerAttachment> getAttachmentBySchoolYear(@QueryParam("school_year") Long schoolYear) {
        try {
            setNamedQuery(WarPlannerAttachment.FIND_ALL_SCHOOL_YEAR);
            putParam("schoolYear", schoolYear);
            return doGetResultList();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), request.getHeaderNames());
        }

        return null;
    }

}
