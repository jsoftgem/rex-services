package com.jsofttechnologies.services.util;

import com.jsofttechnologies.ejb.FlowUserManager;
import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.services.management.WarAgentCrudService;
import com.jsofttechnologies.rexwar.services.management.WarAgentQueryService;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.util.PasswordHash;
import com.jsofttechnologies.util.ProjectConstants;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.Date;

/**
 * Created by Jerico on 7/14/2014.
 */
@Path("services/login_service")
@Stateless
public class LoginService extends FlowService {

    /**
     *
     */
    private static final long serialVersionUID = -7198668697739070832L;

    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;


    @EJB
    private FlowSessionHelper sessionHelper;

    @EJB
    private MergeExceptionSummary exceptionSummary;
    @EJB
    private FlowSessionHelper flowSessionHelper;
    @EJB
    private WarAgentQueryService warAgentQueryService;
    @EJB
    private MessageService messageService;
    @EJB
    private WarAgentCrudService warAgentCrudService;
    @EJB
    private FlowUserManager flowUserManager;

    @POST
    @Path("/auth")
    @SkipCheck("authorization")
    public Response basicAuth(@FormParam("username") String username,
                              @FormParam("password") String password, @Context HttpServletRequest request) {

        if (!isAuthenticated(username, password)) {
            return Response.serverError().entity("{\"msg\":'" + messageService.getMessage(ProjectConstants.MSG_AUTH_FAILED) + "'}").status(ProjectConstants.STATUS_NOT_AUTHENTICATED).build();
        }
        String bs64auth = null;

        try {
            bs64auth = Base64.getEncoder().encodeToString((username + ":" + password + ":" + new Date().getTime()).getBytes());
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
            return Response.serverError().entity("{\"msg\":" + messageService.getMessage(ProjectConstants.MSG_AUTH_FAILED) + "}").status(ProjectConstants.STATUS_NOT_AUTHORIZED).build();
        }

        /*TODO: handle existing session*/
        /*FlowSessionHelper.Promise promise = flowSessionHelper.isSessionExisting(username);
        if (promise.getOk()) {
            //return Response.ok(new ProjectHelper().createJson().addField("username", username).addField("host", promise.getFlowSession().getUserHost()).buildJsonString()).type(MediaType.TEXT_PLAIN_TYPE).status(Response.Status.CONFLICT).build();
        } else {*/
        if (flowUserManager.getGroup(username).getGroupName().equals(WarConstants.AGENT_GROUP)) {
            WarAgent warAgent = warAgentQueryService.findAgentByUsername(flowUserManager.getUser(flowUserManager.getUserId(username)).getUsername());
            if (warAgent.getActive().booleanValue() == Boolean.TRUE) {
                warAgent.setOnline(Boolean.TRUE);
                warAgent.setStartDt(new Date());
                warAgentCrudService.update(warAgent, warAgent.getId());
            } else {
                return Response.serverError().entity("{\"msg\":\"" + messageService.getMessage(WarConstants.MSG_AGENT_NOT_ACTIVE) + "\"}").status(ProjectConstants.STATUS_NOT_AUTHORIZED).build();
            }

        }

        flowSessionHelper.createSession(username, bs64auth, request.getRemoteAddr());


        return Response.ok("{\"bs64auth\":\"" + bs64auth + "\"}").type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    private boolean isAuthenticated(String username, String password) {
        boolean valid = false;
        try {

            FlowUser flowUser = entityManager.createNamedQuery(FlowUser.FIND_BY_USERNAME, FlowUser.class).setParameter("username", username).getSingleResult();

            String userPassword = flowUser.getPassword();

            valid = PasswordHash.validatePassword(password, userPassword);

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        return valid;
    }
}
