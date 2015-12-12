package com.jsofttechnologies.v2.services.admin;

import com.jsofttechnologies.interceptor.UserInfoResource;
import com.jsofttechnologies.jpa.admin.FlowUserGroupModule;
import com.jsofttechnologies.jpa.admin.FlowUserGroupTask;
import com.jsofttechnologies.jpa.dev.FlowModule;
import com.jsofttechnologies.jpa.dev.FlowUserTask;
import com.jsofttechnologies.services.admin.FlowUserGroupModuleQueryService;
import com.jsofttechnologies.services.dev.FlowUserTaskQueryService;
import com.jsofttechnologies.v2.services.resource.PageResource;
import com.jsofttechnologies.v2.util.WarToken;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rickzx98 on 8/31/15.
 */
@Path("services/v2/module")
@Stateless
public class ModuleService extends PageResource<FlowModule, Long> {


    public ModuleService() {
        super(FlowModule.class);
    }

    @Path("group")
    @GET
    public List<FlowModule> getByGroup(@QueryParam("name") String name) {
        return getEntityManager().createNamedQuery(FlowModule.FIND_BY_FLOW_USER_GROUP_NAME, FlowModule.class).setParameter("groupName", name).getResultList();
    }


}
