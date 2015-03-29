package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.jpa.admin.FlowSummary;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 7/23/2014.
 */
@Stateless
@Path("services/flow_summary_query")
public class FlowSummaryQueryService extends QueryService<FlowSummary> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3516614651186863552L;

	public FlowSummaryQueryService() {
		super(FlowSummary.class, FlowSummary.FIND_ALL);
	}
}
