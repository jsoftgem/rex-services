package com.jsofttechnologies.services.util;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.admin.FlowProfilePermission;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserProfile;
import com.jsofttechnologies.services.admin.FlowUserQueryService;
import org.jboss.resteasy.util.Base64;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Jerico on 12/1/2014.
 */
@Path("services/flow_permission")
@Stateless
public class FlowPermissionService {

    @EJB
    private FlowUserQueryService flowUserQueryService;

    @EJB
    private MergeExceptionSummary exceptionSummary;

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
