package com.jsofttechnologies.services.admin;


import com.jsofttechnologies.jpa.admin.FlowUserDetail;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Path("services/flow_user_detail_query")
@Stateless
public class FlowUserDetailQueryService extends QueryService<FlowUserDetail> {

    /**
     *
     */
    private static final long serialVersionUID = -7580157308423505548L;

    public FlowUserDetailQueryService() {
        super(FlowUserDetail.class, FlowUserDetail.FIND_ALL);
    }
}
