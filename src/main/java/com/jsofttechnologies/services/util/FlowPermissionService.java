package com.jsofttechnologies.services.util;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.admin.FlowProfilePermission;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserProfile;
import com.jsofttechnologies.services.admin.FlowUserQueryService;
import org.jboss.resteasy.util.Base64;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

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
    public Boolean hasPermission(@QueryParam("authorization") String authorization, @QueryParam("pageName") String pageName, @QueryParam("method") String method) {
        final String encodedUserPassword = authorization.replaceFirst("Basic ", "");

        //Decode username and password
        String usernameAndPassword = null;
        try {
            usernameAndPassword = new String(Base64.decode(encodedUserPassword));
        } catch (IOException e) {
            exceptionSummary.handleException(e, getClass());
        }

        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        FlowUser flowUser = flowUserQueryService.getFlowUserByUsername(username);
        boolean hasPermission = false;
        profile:
        for (FlowUserProfile flowUserProfile : flowUser.getFlowUserProfileSet()) {
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

        return hasPermission;
    }
}
