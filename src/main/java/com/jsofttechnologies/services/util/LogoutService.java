package com.jsofttechnologies.services.util;

import com.jsofttechnologies.jpa.admin.FlowSession;
import com.jsofttechnologies.security.SessionHelper;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Stateless
@Path("services/logout_service")
public class LogoutService {

    @Context
    private HttpServletRequest request;
    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

    @EJB
    private FlowSessionHelper flowSessionHelper;


    @PermitAll
    @POST
    @Path("/logoff")
    public Response basicLogOff(@HeaderParam("Authorization") String authorization) {
        return flowSessionHelper.logoutSession(authorization);
    }

    @POST
    @Path("logout")
    public Response logout() {
        Response response = null;
        String session = request
                .getHeader(ProjectConstants.HEADER_SESSION_TOKEN);

        Map<String, String> sessionHeaderMap = SessionHelper.getMergeSessionMap(session);

        String sessionId = sessionHeaderMap.get(ProjectConstants.KEY_HEADER_MAP_MSID);

        if (sessionId == null) {
            response = Response.serverError()
                    .status(ProjectConstants.STATUS_NOT_AUTHENTICATED).build();
        } else {
            try {
                FlowSession flowSession = entityManager.createNamedQuery(FlowSession.FIND_BY_SESSIONID, FlowSession.class).setParameter("sessionId", sessionId).getSingleResult();
                flowSession.setActive(false);
                entityManager.merge(flowSession);
                response = Response.ok("{\"msg\":\" Logout successful!\"}",
                        MediaType.APPLICATION_JSON_TYPE).build();
            } catch (Exception e) {
                response = ProjectHelper.error(e);
            }
        }

        return response;
    }
}
