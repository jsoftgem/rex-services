package com.jsofttechnologies.v2.services.war.management;

import com.jsofttechnologies.interceptor.UserInfoResource;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.v2.services.resource.PageResource;
import com.jsofttechnologies.v2.util.WarToken;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by rickzx98 on 8/30/15.
 */
@Stateless
@Path("services/v2/war/agent")
@UserInfoResource
public class AgentService extends PageResource<WarAgent, Long> {

    private WarToken warToken;

    public AgentService() {
        super(WarAgent.class);
    }


    @Override
    public void setWarToken(WarToken warToken) {
        this.warToken = warToken;
    }


    @GET
    @Path("current-agent")
    public WarAgent getCurrentAgent() {

        WarAgent agent = null;
        if (warToken != null) {
            List<WarAgent> warAgentList = getEntityManager()
                    .createNamedQuery(WarAgent.FIND_AGENT_BY_USERNAME, WarAgent.class)
                    .setParameter("username", warToken.getUsername()).getResultList();
            if (warAgentList != null && !warAgentList.isEmpty()) {
                agent = warAgentList.get(0);
            }

        }
        return agent;
    }

}
