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

@Path("services/sync_service")
@Stateless
public class SyncService extends FlowService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5163111692611874512L;

	@Context
	private HttpServletRequest request;
	@PersistenceContext(unitName = ProjectConstants.MAIN_PU)
	private EntityManager entityManager;

	@POST
	@Path("sync")
	public Response sync() {
		Response response = null;

		String sessionId = request
				.getHeader(ProjectConstants.HEADER_SESSION_TOKEN);

		try {
			FlowSession session = entityManager.find(FlowSession.class,
					sessionId);

			FlowUser user = entityManager.find(FlowUser.class,
					session.getUserId());

			SessionModel<FlowUserDetail> sessionModel = new SessionModel<FlowUserDetail>();

			sessionModel.setUserId(user.getId());
			sessionModel.setEmail(user.getEmail());
			sessionModel.setSessionId(sessionId);
			sessionModel.setUsername(user.getUsername());
			//sessionModel.setProfile(user.getProfile());
                        //to do profile checking
			sessionModel.setPassword(user.getPassword());
			sessionModel.setObject(user.getFlowUserDetail());

			response = Response.ok(sessionModel,
					MediaType.APPLICATION_JSON_TYPE).build();
		} catch (Exception e) {
			response = ProjectHelper.error(e);
		}

		return response;
	}

}
