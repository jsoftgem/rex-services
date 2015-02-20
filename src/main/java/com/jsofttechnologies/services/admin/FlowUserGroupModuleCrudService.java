package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.jpa.admin.FlowUserGroupModule;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 11/20/2014.
 */
@Path("services/flow_user_group_module_crud")
@Stateless
public class FlowUserGroupModuleCrudService extends CrudService<FlowUserGroupModule, Long> {

    public FlowUserGroupModuleCrudService() {
        super(FlowUserGroupModule.class);
    }

    @Override
    protected FlowUserGroupModule preCreateValidation(FlowUserGroupModule flowUserGroupModule) throws Exception {
        return flowUserGroupModule;
    }

    @Override
    protected FlowUserGroupModule preUpdateValidation(FlowUserGroupModule flowUserGroupModule) throws Exception {
        return flowUserGroupModule;
    }
}
