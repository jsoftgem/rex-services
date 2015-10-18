package com.jsofttechnologies.services.dev;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.dev.FlowTask;
import com.jsofttechnologies.services.util.QueryService;
import org.jose4j.json.internal.json_simple.JSONObject;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @SkipCheck("authorization")
    @Path("sample_tasks")
    @Produces("application/json")
    @GET
    public Response getSampleTasks(@QueryParam("index") Integer index) {
        Response response = null;
        List countList = entityManager.createNamedQuery(FlowTask.COUNT).getResultList();
        Integer count = Integer.valueOf(countList.get(0).toString());
        Query query = entityManager.createNamedQuery(FlowTask.FIND_ALL).setFirstResult(index).setMaxResults(1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("length", count);
        List<FlowTask> flowTaskList = query.getResultList();
        if (flowTaskList != null && !flowTaskList.isEmpty()) {
            jsonObject.put("value", flowTaskList.get(0));
        }

        response = Response.ok(jsonObject).type(MediaType.APPLICATION_JSON_TYPE).build();

        return response;
    }

}
