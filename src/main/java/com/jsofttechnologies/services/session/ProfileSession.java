package com.jsofttechnologies.services.session;

import com.jsofttechnologies.ejb.FlowUserManager;
import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.admin.*;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.model.management.WarAgentLight;
import com.jsofttechnologies.rexwar.services.management.WarAgentLightQueryService;
import com.jsofttechnologies.rexwar.services.management.WarAgentQueryService;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.admin.FlowUploadedFileQueryService;
import com.jsofttechnologies.services.admin.FlowUserDetailQueryService;
import com.jsofttechnologies.services.util.DownloadService;
import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.services.util.MessageService;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;
import org.json.JSONObject;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Jerico on 12/15/2014.
 */
@Path("session/profile")
@Stateless
public class ProfileSession extends FlowService {


    @EJB
    private WarAgentQueryService warAgentQueryService;
    @EJB
    private WarAgentLightQueryService warAgentLightQueryService;

    @EJB
    private DownloadService downloadService;
    @EJB
    private MessageService messageService;
    @EJB
    private MergeExceptionSummary exceptionSummary;
    @EJB
    private FlowUserManager flowUserManager;
    @EJB
    private FlowUploadedFileQueryService uploadedFileQueryService;
    @EJB
    private FlowUserDetailQueryService flowUserDetailQueryService;


    private static final ServerResponse ACCESS_DENIED = new ServerResponse();
    private static final ServerResponse SERVER_ERROR = new ServerResponse();

    @SkipCheck("action")
    @GET
    @PermitAll
    @Path("emblem")
    public Response getProfileGroupEmblem(@HeaderParam("Authorization") String authorization) {
        Map<String, Object> jsonMap = new HashMap<>();

        if (authorization == null || authorization.isEmpty()) {
            jsonMap.put("msg", messageService.getMessage(ProjectConstants.MSG_LOGIN_REQUIRED));
            ACCESS_DENIED.setEntity(ProjectHelper.json(jsonMap));
            return ACCESS_DENIED;
        }
        //Get encoded username and password
        final String encodedUserPassword = authorization.replaceFirst(ProjectConstants.AUTHENTICATION_SCHEME + " ", "");

        //Decode username and password
        String usernameAndPassword;
        try {
            usernameAndPassword = new String(Base64.decode(encodedUserPassword));
        } catch (IOException e) {
            exceptionSummary.handleException(e, getClass());
            return SERVER_ERROR;

        }
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();


        Long emblemId = flowUserManager.getGroup(username).getEmblemId();

        FlowUploadedFile flowUploadedFile = uploadedFileQueryService.getById(emblemId);

        String path = null;
        if (flowUploadedFile == null) {
            path = ProjectConstants.FLOW_DOWNLOAD_DEFAULT_EMBLEM;
        } else {
            path = flowUploadedFile.getPath();
        }


        return Response.ok(path, MediaType.TEXT_PLAIN_TYPE).build();
    }

    @SkipCheck("action")
    @GET
    @PermitAll
    @Path("user_detail_task")
    public Response getUserDetailTask(@HeaderParam("Authorization") String authorization) {
        String task = "services/flow_task_service/getTask?name=edit_profile&active=true&size=50&showToolBar=false&page=edit_profile&page-path=";
        try {
            Map<String, Object> jsonMap = new HashMap<>();

            if (authorization == null || authorization.isEmpty()) {
                jsonMap.put("msg", messageService.getMessage(ProjectConstants.MSG_LOGIN_REQUIRED));
                ACCESS_DENIED.setEntity(ProjectHelper.json(jsonMap));
                return ACCESS_DENIED;
            }
            //Get encoded username and password
            final String encodedUserPassword = authorization.replaceFirst(ProjectConstants.AUTHENTICATION_SCHEME + " ", "");

            //Decode username and password
            String usernameAndPassword;
            try {
                usernameAndPassword = new String(Base64.decode(encodedUserPassword));
            } catch (IOException e) {
                exceptionSummary.handleException(e, getClass());
                return SERVER_ERROR;
            }
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();

            Long userId = flowUserManager.getUserId(username);

            FlowUser flowUser = flowUserManager.getUser(userId);

            task = task + flowUser.getFlowUserDetail().getId();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }
        return Response.ok(task, MediaType.TEXT_PLAIN).build();
    }

    @SkipCheck("action")
    @GET
    @PermitAll
    @Path("user_detail")
    public Response getUserDetail(@HeaderParam("Authorization") String authorization) {
        StringBuilder userDetail = new StringBuilder("{");
        try {
            Map<String, Object> jsonMap = new HashMap<>();

            FlowSessionHelper.Promise promise = session.isAuthorized(authorization);

            if (promise.getOk()) {

                FlowUser flowUser = promise.getFlowUser();

                FlowUserDetail flowUserDetail = flowUserDetailQueryService.getById(flowUser.getFlowUserDetail().getId());

                FlowUserGroup flowUserGroup = promise.getFlowUserGroup();

                Long emblemId = flowUserGroup.getEmblemId();

                FlowUploadedFile flowUploadedFile = uploadedFileQueryService.getById(emblemId);

                String path = null;

                if (flowUploadedFile == null) {
                    path = ProjectConstants.FLOW_DOWNLOAD_DEFAULT_EMBLEM;
                } else {
                    path = flowUploadedFile.getPath();
                }

                userDetail.append("\"fullName\"").append(":").append("\"").append(flowUserDetail.getFullName()).append("\"").append(",");
                userDetail.append("\"username\"").append(":").append("\"").append(flowUser.getUsername()).append("\"").append(",");
                userDetail.append("\"email\"").append(":").append("\"").append(flowUser.getEmail()).append("\"").append(",");
                userDetail.append("\"avatar\"").append(":").append(flowUserDetail.getAvatar()).append(",");
                userDetail.append("\"detailId\"").append(":").append(flowUserDetail.getId()).append(",");
                userDetail.append("\"group\"").append(":").append("\"").append(flowUserGroup.getGroupTitle()).append("\"").append(",");
                userDetail.append("\"groupEmblem\"").append(":").append("\"").append(path).append("\"").append(",");
                userDetail.append("\"groupOwner\"").append(":").append(flowUser.getId().equals(flowUserGroup.getOwnerUserId())).append(",");
                userDetail.append("\"editProfileTask\"").append(":").append("\"services/flow_task_service/getTask?name=edit_profile&active=true&size=50&showToolBar=false&page=edit_profile&page-path=").append(flowUserDetail.getId()).append("\"").append(",");
                userDetail.append("\"profiles\"").append(":").append("[");

                Iterator<FlowUserProfile> profileIterator = flowUser.getFlowUserProfileSet().iterator();
                while (profileIterator.hasNext()) {
                    FlowUserProfile flowUserProfile = profileIterator.next();
                    userDetail.append("\"").append(flowUserProfile.getProfileName()).append("\"");
                    if (profileIterator.hasNext()) {
                        userDetail.append(",");
                    }
                }
                userDetail.append("]");

                if (flowUserGroup.getGroupName().equals(WarConstants.AGENT_GROUP) || flowUserGroup.getGroupName().equals(WarConstants.AGENT_REGIONAL_MANAGER_GROUP)) {
                    userDetail.append(",");
                    WarAgent warAgent = warAgentQueryService.findAgentByUsername(flowUser.getUsername());
                    WarAgentLight warAgentLight = warAgentLightQueryService.getById(warAgent.getId());
                    userDetail.append("\"agent\":").append(new JSONObject(warAgentLight).toString());
                }

                userDetail.append("}");

            }


        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }
        return Response.ok(userDetail.toString(), MediaType.APPLICATION_JSON_TYPE).build();
    }


}
