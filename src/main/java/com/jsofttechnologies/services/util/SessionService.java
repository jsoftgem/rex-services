package com.jsofttechnologies.services.util;

import com.jsofttechnologies.jpa.admin.FlowSession;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserDetail;
import com.jsofttechnologies.model.SessionModel;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("services/session_service")
public class SessionService extends FlowService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2110460162913103629L;

	@PersistenceContext(unitName = ProjectConstants.MAIN_PU)
	private EntityManager entityManager;

	@Context
	private HttpServletRequest request;

	@POST
	@Path("session")
	public Response getCurrentSession() {
		Response response = null;
		String sessionId = null;
		try {
			sessionId = request
					.getHeader(ProjectConstants.HEADER_SESSION_TOKEN);
		} catch (Exception e) {
			response = ProjectHelper.error(e);
		}
		if (sessionId != null) {
			SessionModel<FlowUserDetail> session = new SessionModel<>();
			try {
				FlowSession flowSession = entityManager.find(
						FlowSession.class, sessionId);

				if (flowSession.getActive()) {
					session.setSessionId(flowSession.getId().toString());
					if (flowSession.getUserId() != null) {
						FlowUser flowUser = entityManager.find(
								FlowUser.class, flowSession.getUserId());
						session.setUserId(flowUser.getId());
						//to do profile checking
						session.setUsername(flowUser.getUsername());

						FlowUserDetail flowUserDetail = entityManager.find(
								FlowUserDetail.class, flowUser
										.getFlowUserDetail().getId());
						session.setObject(flowUserDetail);
					}
					response = Response.ok(session)
							.type(MediaType.APPLICATION_JSON_TYPE).build();
				} else {
					response = Response.serverError()
							.status(ProjectConstants.STATUS_SESSION_TIMEOUT)
							.build();
				}

			} catch (Exception e) {
				response = Response.serverError()
						.status(ProjectConstants.STATUS_NOT_AUTHENTICATED)
						.build();
			}
		}
		return response;
	}

}
