package com.jsofttechnologies.services.dev;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.dev.FlowStyleAsc;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 11/11/2014.
 */
@Path("services/flow_style_asc_query")
public class FlowStyleAscQueryService extends QueryService<FlowStyleAsc> {

    @EJB
    private MergeExceptionSummary exceptionSummary;

    public FlowStyleAscQueryService() {
        super(FlowStyleAsc.class, FlowStyleAsc.FIND_ALL);
    }



}
