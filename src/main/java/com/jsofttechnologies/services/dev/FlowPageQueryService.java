package com.jsofttechnologies.services.dev;

import com.jsofttechnologies.jpa.dev.FlowPage;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.Map;

@Path("services/flow_page_query")
@Stateless
public class FlowPageQueryService extends QueryService<FlowPage> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FlowPageQueryService() {
        super(FlowPage.class, FlowPage.FIND_ALL);
    }


    @GET
    @Path("/name")
    @Produces("application/json")
    public FlowPage getInstanceByName(@QueryParam("name") String name) {
        setNamedQuery(FlowPage.FIND_BY_NAME);
        Map<String, Object> param = new HashMap<>();
        param.put("name", name);
        setParam(param);
        return getSingleResult();
    }

}
