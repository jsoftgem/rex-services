package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.jpa.admin.FlowSession;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.List;

@Path("services/merge_session_query")
@Stateless
public class FlowSessionQueryService extends QueryService<FlowSession> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1785062914216320535L;

	public FlowSessionQueryService() {
		super(FlowSession.class, FlowSession.FIND_ALL);
	}

	public List<FlowSession> getActiveSessions(Long userId) {
		setNamedQuery(FlowSession.FIND_BY_ACTIVE_BY_USER);
		putParam("userId", userId);
		return doGetResultList();
	}

}
