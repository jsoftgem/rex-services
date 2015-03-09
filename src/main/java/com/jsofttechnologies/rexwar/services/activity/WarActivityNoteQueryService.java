package com.jsofttechnologies.rexwar.services.activity;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.rexwar.model.activity.WarActivityNote;
import com.jsofttechnologies.rexwar.util.contants.Month;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by Jerico on 3/8/2015.
 */
@Path("services/war/activity_note_query")
@Stateless
public class WarActivityNoteQueryService extends QueryService<WarActivityNote> {

    public WarActivityNoteQueryService() {
        super(WarActivityNote.class, WarActivityNote.FIND_ALL);
    }

    @Path("get_activity_day")
    @POST
    @SkipCheck("action")
    @Produces(MediaType.APPLICATION_JSON)
    public WarActivityNote findActivityDay(@QueryParam("agentId") Long agentId, @QueryParam("year") Integer year, @QueryParam("month") Month month, @QueryParam("day") Integer day) {
        try {
            setNamedQuery(WarActivityNote.FIND_BY_ACTIVITY_DAY);
            putParam("agentId", agentId);
            putParam("year", year);
            putParam("month", month);
            putParam("day", day);
            return getSingleResult();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), request.getHeaderNames());
        }

        return null;
    }

}
