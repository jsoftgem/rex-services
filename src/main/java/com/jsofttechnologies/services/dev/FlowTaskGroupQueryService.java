package com.jsofttechnologies.services.dev;


import com.jsofttechnologies.jpa.dev.FlowGroup;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Path("services/flow_task_group_query")
@Stateless
public class FlowTaskGroupQueryService extends QueryService<FlowGroup> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FlowTaskGroupQueryService() {
        super(FlowGroup.class, FlowGroup.FIND_ALL);
    }




}
