package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.jpa.admin.FlowUserGroupModule;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.List;

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

    public List<FlowUserGroupModule> findByGroupName(String groupName) {
        List<FlowUserGroupModule> list = null;
        try {
            setNamedQuery(FlowUserGroupModule.FIND_BY_USER_GROUP);
            putParam("groupName", groupName);
            list = doGetResultList();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), groupName);
        }

        return list;
    }

}
