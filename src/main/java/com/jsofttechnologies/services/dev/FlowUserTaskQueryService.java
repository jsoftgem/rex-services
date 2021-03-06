package com.jsofttechnologies.services.dev;

import com.jsofttechnologies.jpa.dev.FlowUserTask;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerico on 11/2/2014.
 */
@Path("services/flow_user_task_query")
@Stateless
public class FlowUserTaskQueryService extends QueryService<FlowUserTask> {

    public FlowUserTaskQueryService() {
        super(FlowUserTask.class, FlowUserTask.FIND_ALL);
    }

    @GET
    @Path("find_by_id/{id}")
    @Produces("application/json")
    public FlowUserTask findById(@PathParam("id") Long id) {
        setNamedQuery(FlowUserTask.FIND_BY_ID);
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        setParam(param);
        return getSingleResult();
    }


    @GET
    @Path("find_by_flow_id/{flowId}")
    @Produces(MediaType.APPLICATION_JSON)
    public FlowUserTask findFlowId(@PathParam("flowId") String flowId) {
        setNamedQuery(FlowUserTask.FIND_BY_FLOW_ID);
        putParam("flowId", flowId);
        return getSingleResult();
    }

    public List<FlowUserTask> findAllByUserId(Long userId) {
        List<FlowUserTask> list = null;
        try {
            setNamedQuery(FlowUserTask.FIND_BY_USER);
            putParam("userId", userId);
            list = doGetResultList();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), userId);
        }
        return list;
    }

}
