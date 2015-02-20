package com.jsofttechnologies.services.dev;

import com.jsofttechnologies.jpa.dev.FlowTask;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Stateless
@Path("services/flow_task_query")
public class FlowTaskQueryService extends QueryService<FlowTask> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FlowTaskQueryService() {
        super(FlowTask.class, FlowTask.FIND_ALL);
    }


    public FlowTask findByName(String name) {
        setNamedQuery(FlowTask.FIND_BY_NAME);
        putParam("name", name);
        return getSingleResult();
    }


}
