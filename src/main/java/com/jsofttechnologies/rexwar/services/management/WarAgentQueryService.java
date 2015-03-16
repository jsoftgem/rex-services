package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Jerico on 1/11/2015.
 */
@Path("services/war/agent_query")
@Stateless
public class WarAgentQueryService extends QueryService<WarAgent> {

    public WarAgentQueryService() {
        super(WarAgent.class, WarAgent.FIND_ALL);
    }


    public WarAgent findAgentByUsername(String username) {
        try {
            setNamedQuery(WarAgent.FIND_AGENT_BY_USERNAME);
            putParam("username", username);
            List<WarAgent> agentList = doGetResultList();
            if (agentList != null && !agentList.isEmpty()) {
                return agentList.get(0);
            }
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        return null;
    }

    @POST
    @Path("find_manager_by_region/{region}")
    @Produces(MediaType.APPLICATION_JSON)
    public WarAgent findManagerByRegion(@PathParam("region") String region) {
        WarAgent warAgent = null;
        try {
            setNamedQuery(WarAgent.FIND_MANAGER_BY_REGION);
            putParam("region", region);
            warAgent = getSingleResult();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), region);
        }
        return warAgent;
    }


    @GET
    @Path("find_by_region/{region}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WarAgent> findByRegion(@PathParam("region") String region) {
        List<WarAgent> warAgents = null;
        try {
            setNamedQuery(WarAgent.FIND_BY_REGION);
            putParam("region", region);
            warAgents = doGetResultList();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), region);
        }
        return warAgents;
    }


}
