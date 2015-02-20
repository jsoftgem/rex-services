package com.jsofttechnologies.services.dev;


import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.dev.FlowStyle;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("services/flow_style_query")
@Stateless
public class FlowStyleQueryService extends QueryService<FlowStyle> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private MergeExceptionSummary mergeExceptionSummary;

    public FlowStyleQueryService() {
        super(FlowStyle.class, FlowStyle.FIND_ALL);
    }


    @Path("getCompStyles")
    @GET
    @Produces("application/json")
    public List<FlowStyle> getCompStyles(@QueryParam("compId") Long compId, @QueryParam("compType") String compType) {
        List<FlowStyle> flowStyles = null;
        setNamedQuery(FlowStyle.FIND_BY_COMP_ID_AND_TYPE);

        Map<String, Object> params = new HashMap<>();
        params.put("compId", compId);
        params.put("compType", compType);

        setParam(params);

        try {
            flowStyles = doGetResultList();
        } catch (Exception e) {
            mergeExceptionSummary.handleException(e, getClass());
        }

        return flowStyles;
    }
}
