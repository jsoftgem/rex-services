package com.jsofttechnologies.services.util;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.admin.FlowProfilePermission;
import com.jsofttechnologies.jpa.admin.FlowUserProfile;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.admin.FlowUserQueryService;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Jerico on 12/1/2014.
 */
@Path("services/flow_permission")
@Stateless
public class FlowPermissionService extends FlowService {

    @EJB
    private FlowUserQueryService flowUserQueryService;

    @EJB
    private MergeExceptionSummary exceptionSummary;

    public Boolean hasProfileEJB(String profiles) {

        String[] split = profiles.split(",");

        String authorization = request.getHeader("Authorization");

        FlowSessionHelper.Promise promise = session.isAuthorized(authorization);

        boolean valid = false;

        if (promise.getOk()) {
            if (promise.getFlowUserGroup().getGroupName().equals(WarConstants.AGENT_GROUP)) {
                parentLoop:
                for (String prof : split) {
                    for (FlowUserProfile profile : promise.getFlowUser().getFlowUserProfileSet()) {
                        if (prof.equals(profile.getProfileName())) {
                            valid = true;
                            break parentLoop;
                        }

                    }

                }
            }
        }

        return valid;
    }


    @POST
    @Path("has_profile")
    @SkipCheck("action")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response hasProfile(String profiles) {

        String[] split = profiles.split(",");
        Response response = null;
        String authorization = request.getHeader("Authorization");

        FlowSessionHelper.Promise promise = session.isAuthorized(authorization);

        boolean valid = false;

        if (promise.getOk()) {
            parentLoop:
            for (String prof : split) {
                for (FlowUserProfile profile : promise.getFlowUser().getFlowUserProfileSet()) {
                    if (prof.equals(profile.getProfileName())) {
                        valid = true;
                        break parentLoop;
                    }

                }

            }

        }

        response = Response.ok(valid, MediaType.APPLICATION_JSON_TYPE).build();

        return response;
    }

    @GET
    @Path("has_permission")
    @Produces("application/json")
    @PermitAll
    public Boolean hasPermission(@HeaderParam("authorization") String authorization, @QueryParam("pageName") String pageName, @QueryParam("method") String method) {
        FlowSessionHelper.Promise promise = session.isAuthorized(authorization);

        boolean hasPermission = false;

        if (promise.getOk()) {
            profile:
            for (FlowUserProfile flowUserProfile : promise.getFlowUser().getFlowUserProfileSet()) {
                if (hasPermission) break profile;
                List<FlowProfilePermission> flowProfilePermissionList = flowUserProfile.getFlowProfilePermissions();
                permission:
                for (FlowProfilePermission flowProfilePermission : flowProfilePermissionList) {
                    if (flowProfilePermission.getFlowPageName().equals(pageName)) {
                        if (method == null) {
                            hasPermission = true;
                            break permission;
                        }
                        if (method.equalsIgnoreCase("get")) {
                            hasPermission = flowProfilePermission.getGet();
                        } else if (method.equalsIgnoreCase("delete")) {
                            hasPermission = flowProfilePermission.getDel();
                        } else if (method.equalsIgnoreCase("post")) {
                            hasPermission = flowProfilePermission.getPost();
                        } else if (method.equalsIgnoreCase("put")) {
                            hasPermission = flowProfilePermission.getPut();
                        }
                        break permission;
                    }
                }

            }
        }

        return hasPermission;
    }
}
