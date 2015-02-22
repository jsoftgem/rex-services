package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.rexwar.model.activity.WarCustomerMarket;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Jerico on 1/31/2015.
 */
@Path("services/war/war_customer_market_query")
@Stateless
public class WarCustomerMarketQueryService extends QueryService<WarCustomerMarket> {

    public WarCustomerMarketQueryService() {
        super(WarCustomerMarket.class, WarCustomerMarket.FIND_ALL);
    }


    @Path("find_by_school_year")
    @GET
    @SkipCheck("action")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WarCustomerMarket> findBySchoolYear(@QueryParam("schoolYear") Long schoolYear) {

        setNamedQuery(WarCustomerMarket.FIND_BY_SCHOOL_YEAR);
        putParam("schoolYear", schoolYear);

        return doGetResultList();
    }


    @Path("find_by_agent")
    @GET
    @SkipCheck("action")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WarCustomerMarket> findByAgent(@QueryParam("schoolYear") Long schoolYear, @QueryParam("agent") Long agent) {
        setNamedQuery(WarCustomerMarket.FIND_BY_SCHOOL_YEAR_AGENT);
        putParam("schoolYear", schoolYear);
        putParam("agent", agent);
        return doGetResultList();
    }


    @Path("find_by_agent_customer")
    @GET
    @SkipCheck("action")
    @Produces(MediaType.APPLICATION_JSON)
    public WarCustomerMarket findByAgentCustomer(@QueryParam("schoolYear") Long schoolYear, @QueryParam("agent") Long agent, @QueryParam("customer") Long customerId) {
        setNamedQuery(WarCustomerMarket.FIND_BY_SCHOOL_YEAR_AGENT_CUSTOMER);
        putParam("schoolYear", schoolYear);
        putParam("agent", agent);
        putParam("customer", customerId);
        return getSingleResult();
    }


}
