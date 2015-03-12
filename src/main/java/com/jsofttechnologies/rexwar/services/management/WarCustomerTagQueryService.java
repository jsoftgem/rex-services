package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.rexwar.model.management.WarCustomerTag;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Jerico on 3/7/2015.
 */
@Path("services/war/customer_tag_query")
@Stateless
public class WarCustomerTagQueryService extends QueryService<WarCustomerTag> {


    public WarCustomerTagQueryService() {
        super(WarCustomerTag.class);
    }

    @Path("by_assigned_agents")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<WarCustomerTag> getByAssignedAgent(@QueryParam("agentId") Long agentId) {
        List<WarCustomerTag> warCustomerTags = null;
        try {
            setNamedQuery(WarCustomerTag.FIND_BY_AGENT);
            putParam("agentId", agentId);

            warCustomerTags = doGetResultList();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), agentId);
        }

        return warCustomerTags;
    }


    @Path("by_agent_top")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<WarCustomerTag> getByTop(@QueryParam("agentId") Long agentId, @QueryParam("size") @DefaultValue("20") Integer size) {
        List<WarCustomerTag> warCustomerTags = null;
        try {
            setNamedQuery(WarCustomerTag.FIND_BY_AGENT);
            putParam("agentId", agentId);
            setMax(size);
            warCustomerTags = doGetResultList();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), agentId);
        }

        return warCustomerTags;
    }


    @Path("is_top_20")
    /*TODO: determine top 20 customer*/
    public Boolean isTop20(@QueryParam("customerId") Long customerId, @QueryParam("agentId") Long agentId) {

        List<WarCustomerTag> warCustomerTags = null;
        try {
            setNamedQuery(WarCustomerTag.FIND_IF_TOP_20);
            putParam("agentId", agentId);
            putParam("customerId", customerId);

            warCustomerTags = doGetResultList();

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        return warCustomerTags != null && !warCustomerTags.isEmpty();
    }


}
