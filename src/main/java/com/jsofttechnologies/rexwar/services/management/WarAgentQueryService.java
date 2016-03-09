package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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


    @GET
    @Path("find_agent_by_current_user_level")
    public Response findAgentByCurrentUser(@HeaderParam("Authorization") String authorization) {
        Response response = null;

        try {

            FlowSessionHelper.Promise authorized = session.isAuthorized(authorization);

            if (authorized.getOk()) {

                FlowUserGroup flowUserGroup = authorized.getFlowUserGroup();

                switch (flowUserGroup.getGroupName()) {

                    case WarConstants.AGENT_GROUP:
                       break;
                    case WarConstants.AGENT_GENERAL_MANAGER_GROUP:
                        setNamedQuery(WarAgent.FIND_ALL);
                        response = Response.ok(doGetResultList(), MediaType.APPLICATION_JSON_TYPE).build();
                        break;
                    case WarConstants.AGENT_REGIONAL_MANAGER_GROUP:
                        WarAgent warAgent = findAgentByUsername(authorized.getFlowUser().getUsername());
                        setNamedQuery(WarAgent.FIND_AGENT_BY_MANAGER);
                        putParam("region", warAgent.getRegion());
                        response = Response.ok(doGetResultList(), MediaType.APPLICATION_JSON_TYPE).build();
                        break;
                    default:
                        setNamedQuery(WarAgent.FIND_ALL_NO_MANAGER);
                        response = Response.ok(doGetResultList(), MediaType.APPLICATION_JSON_TYPE).build();
                        break;
                }


            } else {
                response = authorized.getResponse();
            }


        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), authorization);
        }

        return response;
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
        List<WarAgent> agents = null;
        WarAgent warAgent = null;
        try {
            setNamedQuery(WarAgent.FIND_MANAGER_BY_REGION);
            putParam("region", region);
            agents = doGetResultList();

            if (agents != null && !agents.isEmpty()) {
                warAgent = agents.get(0);
            }
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

    public WarAgent findByInitials(String initials) {
        List<WarAgent> agents = null;
        try {
            setNamedQuery(WarAgent.FIND_BY_INITIALS);
            putParam("initials", initials.toLowerCase());
            agents = doGetResultList();

            if (agents != null && !agents.isEmpty()) {
                return agents.get(0);
            }
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), initials);
        }

        return null;
    }

}
