package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.rexwar.model.management.WarCustomerLight;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerico on 1/28/2015.
 */
@Path("services/war/customer_light_query")
@Stateless
public class WarCustomerLightQueryService extends QueryService<WarCustomerLight> {
    public WarCustomerLightQueryService() {
        super(WarCustomerLight.class, WarCustomerLight.FIND_ALL);
    }

    @POST
    @Path("find_by_assigned_agent")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WarCustomerLight> getByAssignedAgent(@QueryParam("agentId") Long agentId) {
        List<WarCustomerLight> warCustomerLights = new ArrayList<>();
        try {
            setNamedQuery(WarCustomerLight.FIND_BY_ASSIGNED_AGENT);
            putParam("agentId", agentId);
            warCustomerLights = doGetResultList();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), agentId);
        }

        return warCustomerLights;
    }

}
