package com.jsofttechnologies.services.session;

import com.jsofttechnologies.ejb.FlowUserManager;
import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserProfile;
import com.jsofttechnologies.jpa.flow.FlowNotification;
import com.jsofttechnologies.services.admin.FlowUserDetailQueryService;
import com.jsofttechnologies.services.flow.FlowNotificationCrudService;
import com.jsofttechnologies.services.flow.FlowNotificationQueryService;
import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.services.util.MessageService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Jerico on 12/22/2014.
 */
@Path("session/notification")
@Stateless
public class NotificationSession extends FlowService{

    @EJB
    private MessageService messageService;
    @EJB
    private MergeExceptionSummary exceptionSummary;
    @EJB
    private FlowUserManager flowUserManager;
    @EJB
    private FlowUserDetailQueryService flowUserDetailQueryService;
    @EJB
    private FlowSessionHelper flowSessionHelper;
    @EJB
    private FlowNotificationQueryService flowNotificationQueryService;
    @EJB
    private FlowNotificationCrudService flowNotificationCrudService;

    public void addToQueue(FlowUser flowUser, String group, String message, String messageType, Boolean global, String alertType, Long startDate) {
        FlowNotification flowNotification = new FlowNotification();
        flowNotification.setUserId(flowUser.getId());
        flowNotification.setMessageType(messageType);
        flowNotification.setMessage(message);
        flowNotification.setAlarmType(alertType);
        flowNotification.setGlobal(global);
        flowNotification.setGroup(group);
        flowNotification.setStartDt(new Date(startDate));
        Set<FlowUserProfile> flowUserProfileSet = flowUser.getFlowUserProfileSet();
        Iterator<FlowUserProfile> flowUserProfileIterator = flowUserProfileSet.iterator();
        StringBuilder profileBuilder = new StringBuilder();
        while (flowUserProfileIterator.hasNext()) {
            profileBuilder.append(flowUserProfileIterator.next().getProfileName());
            if (flowUserProfileIterator.hasNext()) {
                profileBuilder.append(",");
            }
        }
        flowNotification.setProfiles(profileBuilder.toString());
        flowNotificationCrudService.create(flowNotification);
    }

    @SkipCheck("action")
    @GET
    @Path("queue")
    public Response queue(@HeaderParam("Authorization") String authorization,
                          @QueryParam("message") String message,
                          @QueryParam("messageType") @DefaultValue("info") String messageType,
                          @QueryParam("global") @DefaultValue("true") Boolean global,
                          @QueryParam("alert-type") @DefaultValue("silent") String alertType,
                          @QueryParam("task") @DefaultValue("notification") String task,
                          @QueryParam("page") @DefaultValue("notification_view") String page,
                          @QueryParam("page-param") String param,
                          @QueryParam("start-date") Long startDate) {
        Response response = null;
        FlowSessionHelper.Promise promise = flowSessionHelper.isAuthorized(authorization);

        if (startDate == null) {
            startDate = new Date().getTime();
        }

        if (promise.getOk()) {
            FlowNotification flowNotification = new FlowNotification();
            flowNotification.setUserId(promise.getFlowUser().getId());
            flowNotification.setMessageType(messageType);
            flowNotification.setMessage(message);
            flowNotification.setAlarmType(alertType);
            flowNotification.setGlobal(global);
            flowNotification.setGroup(promise.getFlowUserGroup().getGroupName());
            flowNotification.setStartDt(new Date(startDate));
            flowNotification.setPage(page);
            flowNotification.setTask(task);
            flowNotification.setPageParam(param);
            Set<FlowUserProfile> flowUserProfileSet = promise.getFlowUser().getFlowUserProfileSet();

            Iterator<FlowUserProfile> flowUserProfileIterator = flowUserProfileSet.iterator();
            StringBuilder profileBuilder = new StringBuilder();

            while (flowUserProfileIterator.hasNext()) {
                profileBuilder.append(flowUserProfileIterator.next().getProfileName());
                if (flowUserProfileIterator.hasNext()) {
                    profileBuilder.append(",");
                }
            }
            flowNotification.setProfiles(profileBuilder.toString());

            flowNotificationCrudService.create(flowNotification);

        } else {
            response = promise.getResponse();
        }

        return response;
    }

    @SkipCheck("action")
    @Path("top")
    @GET
    public Response getTop(@HeaderParam("Authorization") String authorization, @QueryParam("limit") int limit) {
        Response response = null;

        FlowSessionHelper.Promise promise = flowSessionHelper.isAuthorized(authorization);

        if (promise.getOk()) {

            String username = promise.getFlowUser().getUsername();
            if (flowUserManager.isGroupAdmin(username)) {
                response = Response.ok(flowNotificationQueryService.getGroupTop(limit, promise.getFlowUserGroup().getGroupName()), MediaType.APPLICATION_JSON_TYPE).build();
            } else {
                response = Response.ok(flowNotificationQueryService.getUserTop(limit, promise.getFlowUser().getId()), MediaType.APPLICATION_JSON_TYPE).build();
            }

        } else {
            response = promise.getResponse();
        }

        return response;
    }

    @SkipCheck("action")
    @Path("all")
    @GET
    public Response getAll(@HeaderParam("Authorization") String authorization) {
        Response response = null;

        FlowSessionHelper.Promise promise = flowSessionHelper.isAuthorized(authorization);

        if (promise.getOk()) {

            String username = promise.getFlowUser().getUsername();
            if (flowUserManager.isGroupAdmin(username)) {
                response = Response.ok(flowNotificationQueryService.getGroupAll(promise.getFlowUserGroup().getGroupName()), MediaType.APPLICATION_JSON_TYPE).build();
            } else {
                response = Response.ok(flowNotificationQueryService.getUserAll(promise.getFlowUser().getId()), MediaType.APPLICATION_JSON_TYPE).build();
            }

        } else {
            response = promise.getResponse();
        }

        return response;
    }

    @SkipCheck("action")
    @Path("alerts")
    @GET
    public Response getAlerts(@HeaderParam("Authorization") String authorization) {
        Response response = null;

        FlowSessionHelper.Promise promise = flowSessionHelper.isAuthorized(authorization);

        if (promise.getOk()) {

            String username = promise.getFlowUser().getUsername();
            if (flowUserManager.isGroupAdmin(username)) {
                response = Response.ok(flowNotificationQueryService.getGroupAlert(promise.getFlowUserGroup().getGroupName()), MediaType.APPLICATION_JSON_TYPE).build();
            } else {
                response = Response.ok(flowNotificationQueryService.getUserAlert(promise.getFlowUser().getId()), MediaType.APPLICATION_JSON_TYPE).build();
            }

        } else {
            response = promise.getResponse();
        }

        return response;
    }

    @SkipCheck("action")
    @Path("notify")
    @PUT
    public Response notify(FlowNotification notification) {
        notification.setNotified(Boolean.TRUE);
        return flowNotificationCrudService.update(notification, notification.getId());
    }

    @SkipCheck("action")
    @Path("getAlert")
    @GET
    public Response getAlert(@HeaderParam("Authorization") String authorization, @QueryParam("alert") Long alertId) {
        Response response = null;

        FlowSessionHelper.Promise promise = flowSessionHelper.isAuthorized(authorization);

        if (promise.getOk()) {

            response = Response.ok(flowNotificationQueryService.getById(alertId), MediaType.APPLICATION_JSON_TYPE).build();

        } else {
            response = promise.getResponse();
        }

        return response;
    }


}
