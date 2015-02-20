package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.jpa.admin.FlowUserGroupTask;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 11/2/2014.
 */
@Path("services/flow_user_group_task_crud")
@Stateless
public class FlowUserGroupTaskCrudService extends CrudService<FlowUserGroupTask, Long> {
    public FlowUserGroupTaskCrudService() {
        super(FlowUserGroupTask.class);
    }

    @Override
    protected FlowUserGroupTask preCreateValidation(FlowUserGroupTask flowUserGroupTask) throws Exception {
        return flowUserGroupTask;
    }

    @Override
    protected FlowUserGroupTask preUpdateValidation(FlowUserGroupTask flowUserGroupTask) throws Exception {
        return flowUserGroupTask;
    }
}
