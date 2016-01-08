package com.jsofttechnologies.rexwar.services.activity;

import com.jsofttechnologies.interceptor.Notify;
import com.jsofttechnologies.jpa.util.FlowAlertType;
import com.jsofttechnologies.rexwar.model.activity.WarActivity;
import com.jsofttechnologies.rexwar.model.activity.WarPlanner;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.model.management.WarCustomerRegion;
import com.jsofttechnologies.rexwar.services.data.WarRegionQueryService;
import com.jsofttechnologies.rexwar.services.management.WarAgentQueryService;
import com.jsofttechnologies.rexwar.services.management.WarCustomerTagQueryService;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.util.CrudService;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.util.CalendarUtil;
import com.jsofttechnologies.util.ProjectHelper;
import org.apache.commons.lang.StringUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jerico on 1/30/2015.
 */
@Path("services/war/activity_crud")
@Stateless
@Notify(task = "planner_task", alertType = FlowAlertType.BROADCAST, page = "planner")
public class WarActivityCrudService extends CrudService<WarActivity, Long> {

    @EJB
    private FlowSessionHelper sessionHelper;

    @EJB
    private WarPlannerQueryService warPlannerQueryService;

    @EJB
    private WarRegionQueryService regionQueryService;

    @EJB
    private WarAgentQueryService warAgentQueryService;

    @EJB
    private WarCustomerTagQueryService warCustomerTagQueryService;

    public WarActivityCrudService() {
        super(WarActivity.class);
    }

    @Override
    protected WarActivity preCreateValidation(WarActivity warActivity) throws Exception {
        WarAgent warAgent = warAgentQueryService.getById(warActivity.getAgentId());
        WarCustomerRegion region = regionQueryService.findByCode(warAgent.getRegion());
        warActivity.setRegionId(region.getId());
        warActivity.setRegionCode(region.getRegionCode());
        setWorkWith(warActivity, warAgent);
        return warActivity;
    }

    @Override
    protected WarActivity preUpdateValidation(WarActivity warActivity) throws Exception {
        if ((warActivity.getEcd() != null && warActivity.getEcd()) ||
                (warActivity.getIte() != null && warActivity.getIte()) ||
                (warActivity.getCoe() != null && warActivity.getCoe()) ||
                (warActivity.getFp() != null && warActivity.getFp()) ||
                (warActivity.getGd() != null && warActivity.getGd()) ||
                (warActivity.getDoi() != null && warActivity.getDoi()) ||
                (warActivity.getPo() != null && warActivity.getPo()) ||
                (warActivity.getDaotrc() != null && warActivity.getDaotrc()) ||
                (warActivity.getBookList() != null && warActivity.getBookList()) ||
                (warActivity.getUcis() != null && warActivity.getUcis()) ||
                (warActivity.getIes() != null && warActivity.getIes()) ||
                StringUtils.isNotEmpty(warActivity.getCustomerSpecificActivity())) {
            warActivity.setActual(Boolean.TRUE);
            setWorkWith(warActivity, null);
        }
        return warActivity;
    }

    private void setWorkWith(WarActivity warActivity, WarAgent warAgent) {
        warAgent = warAgent != null ? warAgent : warAgentQueryService.getById(warActivity.getAgentId());
        FlowSessionHelper.Promise authorized = getUserSession();
        if (authorized.isGroup(WarConstants.AGENT_REGIONAL_MANAGER_GROUP)) {
            warActivity.setWorkedWith(Boolean.TRUE);
            warActivity.setManagerId(warAgent.getId());
        } else if (authorized.isGroup(WarConstants.AGENT_GROUP) && (warActivity.getWorkedWith() != null && warActivity.getWorkedWith().equals(Boolean.TRUE))) {
            WarAgent agentManager = warAgentQueryService.findManagerByRegion(warAgent.getRegion());
            warActivity.setManagerId(agentManager.getId());
        }
    }


    @Path("save_activities/{id:[0-9][0-9]*}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveActivities(WarActivity[] activities, @PathParam("id") Long plannerId) {
        Response response = null;

        Date currentDate = new Date();

        Calendar current = Calendar.getInstance();

        current.setTime(currentDate);

        int currentWeek = current.get(Calendar.WEEK_OF_YEAR);
        int currentYear = current.get(Calendar.YEAR);
        int currentDayOfWeek = current.get(Calendar.DAY_OF_WEEK);
        try {
            WarPlanner warPlanner = warPlannerQueryService.getById(plannerId);

            for (WarActivity activity : activities) {
                if (activity.getId() != null) {
                    response = update(activity, activity.getId());
                } else if (activity.getId() == null) {

                    activity.setWarPlanner(warPlanner);
                    Calendar activityCalendar = Calendar.getInstance();

                    activityCalendar.setTime(activity.getStartDt());

                    int week = activityCalendar.get(Calendar.WEEK_OF_YEAR);
                    int year = activityCalendar.get(Calendar.YEAR);

                    if (currentYear > year) {
                        activity.setPlanned(Boolean.FALSE);
                    } else {
                        if (currentWeek > week) {
                            activity.setPlanned(Boolean.FALSE);
                        } else if (currentWeek == week && currentDayOfWeek != Calendar.SUNDAY) {
                            activity.setPlanned(Boolean.FALSE);
                        } else {
                            activity.setPlanned(Boolean.TRUE);
                        }
                    }

                    activity.setWeek(CalendarUtil.getWeek(activity.getStartDt()));
                    response = create(activity);
                }
                if (response.getStatus() > 400) break;
            }

            response = Response.ok(new ProjectHelper().createJson().addField("plannedId", warPlanner.getId()).buildJsonString(), MediaType.APPLICATION_JSON_TYPE).build();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        return response;
    }

    @Path("delete_activity/{id:[0-9][0-9]*}")
    @DELETE
    public void deleteActivity(@PathParam("id") Long activityId) throws Exception {
        setId(activityId);
        getInstance().setDeleted(Boolean.TRUE);
        save();
    }

    @Override
    public String updateSuccessMessage(WarActivity activity) {

        String[] params = new String[2];

        String authorization = request.getHeader("Authorization");
        FlowSessionHelper.Promise promise = sessionHelper.isAuthorized(authorization);

        if (promise.getOk()) {
            params[0] = promise.getFlowUser().getFlowUserDetail().getFullName();
            params[1] = activity.getStartDt().toString();

        }


        return MessageFormat.format(getMessage("WAR_ACTIVITY_USER_UPDATED"), params);
    }

}
