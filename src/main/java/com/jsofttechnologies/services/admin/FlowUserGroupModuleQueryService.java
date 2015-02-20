package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.jpa.admin.FlowUserGroupModule;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 11/20/2014.
 */
@Path("services/flow_user_group_module_query")
@Stateless
public class FlowUserGroupModuleQueryService extends QueryService<FlowUserGroupModule> {

    public FlowUserGroupModuleQueryService() {
        super(FlowUserGroupModule.class, FlowUserGroupModule.FIND_ALL);
    }


    public FlowUserGroupModule findByGroupAndTask(String groupName, Long taskId) {

        setNamedQuery(FlowUserGroupModule.FIND_BY_USER_GROUP_AND_TASK);
        putParam("groupName", groupName);
        putParam("taskId", taskId);

        return getSingleResult();
    }

}
