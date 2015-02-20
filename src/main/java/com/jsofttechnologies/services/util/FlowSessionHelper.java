package com.jsofttechnologies.services.util;

import com.jsofttechnologies.ejb.FlowUserManager;
import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.admin.FlowSession;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.services.management.WarAgentCrudService;
import com.jsofttechnologies.rexwar.services.management.WarAgentQueryService;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.admin.FlowSessionCrudService;
import com.jsofttechnologies.services.admin.FlowSessionQueryService;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

/**
 * Created by Jerico on 12/22/2014.
 */
@Stateless
public class FlowSessionHelper {

    @EJB
    private MessageService messageService;
    @EJB
    private MergeExceptionSummary exceptionSummary;

    @EJB
    private FlowUserManager flowUserManager;

    @EJB
    private FlowSessionQueryService flowSessionQueryService;
    @EJB
    private FlowSessionCrudService flowSessionCrudService;
    @EJB
    private WarAgentQueryService warAgentQueryService;
    @EJB
    private WarAgentCrudService warAgentCrudService;

    private static final ServerResponse ACCESS_DENIED = new ServerResponse();
    private static final ServerResponse SERVER_ERROR = new ServerResponse();

    public Promise isSessionExisting(String username) {
        Promise promise = new Promise();
        Long userId = flowUserManager.getUserId(username);
        List<FlowSession> sessionList = flowSessionQueryService.getActiveSessions(userId);

        promise.ok = sessionList != null && !sessionList.isEmpty();

        if (promise.ok) {
            promise.flowSession = sessionList.get(0);
        }

        return promise;
    }

    public void createSession(String username, String remoteAddress) {
        Long userId = flowUserManager.getUserId(username);
        FlowSession flowSession = new FlowSession();
        flowSession.setActive(Boolean.TRUE);
        flowSession.setUserHost(remoteAddress);
        flowSession.setUserId(userId);
        flowSession.setLastAccessDate(new Date());
        flowSession.setSecured(Boolean.TRUE/*TODO: create a service to determine if its an authorized host/user*/);
        flowSessionCrudService.create(flowSession);
    }

    public Response logoutSession(String authorization) {
        Promise promise = isAuthorized(authorization);

        if (promise.getOk()) {
            FlowSession flowSession = promise.getFlowSession();
            flowSession.setActive(Boolean.FALSE);
            flowSessionCrudService.update(flowSession, flowSession.getId());

            if (promise.getFlowUserGroup().getGroupName().equals(WarConstants.AGENT_GROUP)) {
                WarAgent warAgent = warAgentQueryService.findAgentByUsername(promise.getFlowUser().getUsername());
                warAgent.setOnline(Boolean.FALSE);
                warAgentCrudService.update(warAgent, warAgent.getId());
            }

            return Response.ok().build();
        } else {
            return promise.getResponse();
        }

    }

    public Promise isAuthorized(String authorization) {
        Promise promise = new Promise();
        promise.ok = true;
        Map<String, Object> jsonMap = new HashMap<>();

        if (authorization == null || authorization.isEmpty()) {
            jsonMap.put("msg", messageService.getMessage(ProjectConstants.MSG_LOGIN_REQUIRED));
            ACCESS_DENIED.setEntity(ProjectHelper.json(jsonMap));
            promise.response = ACCESS_DENIED;
            promise.ok = false;
        }
        //Get encoded username and password
        final String encodedUserPassword = authorization.replaceFirst(ProjectConstants.AUTHENTICATION_SCHEME + " ", "");

        //Decode username and password
        String usernameAndPassword = null;
        try {
            usernameAndPassword = new String(Base64.decode(encodedUserPassword));
        } catch (IOException e) {
            exceptionSummary.handleException(e, getClass());
            promise.response = SERVER_ERROR;
            promise.ok = false;

        }
        if (promise.getOk()) {
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            promise.flowUser = flowUserManager.getUser(flowUserManager.getUserId(username));
            promise.flowUserGroup = flowUserManager.getGroup(username);

            Promise sessionPromise = isSessionExisting(username);
            if (sessionPromise.getOk()) {
                promise.flowSession = sessionPromise.getFlowSession();
            }
        }

        return promise;
    }

    public static final class Promise {

        private Boolean ok;
        private Response response;
        private FlowUser flowUser;
        private FlowUserGroup flowUserGroup;
        private FlowSession flowSession;

        public Boolean getOk() {
            return ok;
        }

        public Response getResponse() {
            return response;
        }

        public FlowUser getFlowUser() {
            return flowUser;
        }

        public FlowUserGroup getFlowUserGroup() {
            return flowUserGroup;
        }

        public FlowSession getFlowSession() {
            return flowSession;
        }
    }

}
