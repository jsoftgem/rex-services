package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by Jerico on 1/11/2015.
 */
@Path("services/war/agent_query")
@Stateless
public class WarAgentQueryService extends QueryService<WarAgent> {
    @EJB
    private MergeExceptionSummary exceptionSummary;

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

}
