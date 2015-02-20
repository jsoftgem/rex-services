package com.jsofttechnologies.services.dev;


import com.jsofttechnologies.jpa.dev.FlowModule;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Path("services/flow_module_query")
@Stateless
public class FlowModuleQueryService extends QueryService<FlowModule> {

    public FlowModuleQueryService() {
        super(FlowModule.class, FlowModule.FIND_ALL);
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;


}
