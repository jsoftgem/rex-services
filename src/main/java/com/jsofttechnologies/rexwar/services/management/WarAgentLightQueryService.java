package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.model.management.WarAgentLight;
import com.jsofttechnologies.rexwar.model.management.WarCustomerRegion;
import com.jsofttechnologies.rexwar.services.data.WarRegionQueryService;
import com.jsofttechnologies.services.util.QueryService;
import com.jsofttechnologies.util.ProjectHelper;
import org.json.JSONArray;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Jerico on 2/26/2015.
 */
@Stateless
@Path("services/war/agent_light_query")
public class WarAgentLightQueryService extends QueryService<WarAgentLight> {
    @EJB
    private WarRegionQueryService regionQueryService;

    public WarAgentLightQueryService() {
        super(WarAgentLight.class, WarAgentLight.FIND_ALL);
    }


    @POST
    @Path("find_managed_agents")
    @SkipCheck("action")
    public Response findManagedAgents(@QueryParam("manager") Long manager) {
        Response response = null;
        try {


            WarAgentLight warManager = getById(manager);
            WarCustomerRegion region = regionQueryService.findByCode(warManager.getRegion());

            setNamedQuery(WarAgent.FIND_AGENT_BY_MANAGER);

            putParam("region", warManager.getRegion());

            List<WarAgentLight> agents = doGetResultList();


            ProjectHelper returnVal = new ProjectHelper();

            returnVal.createJson()
                    .addField("manager", warManager)
                    .addField("region", region)
                    .addField("agents", new JSONArray(agents.toArray()));

            response = Response.ok(agents, MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), "findManagedAgents", manager);
            response = ProjectHelper.error("Error getting managed agents");
        }


        return response;
    }

}
