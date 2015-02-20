package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.jpa.admin.FlowUserGroupTask;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 11/2/2014.
 */
@Path("services/flow_user_group_task_query")
@Stateless
public class FlowUserGroupTaskQueryService extends QueryService<FlowUserGroupTask> {
    public FlowUserGroupTaskQueryService() {
        super(FlowUserGroupTask.class);
    }
}
