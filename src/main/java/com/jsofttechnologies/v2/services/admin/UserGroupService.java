package com.jsofttechnologies.v2.services.admin;

import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.v2.services.resource.PageResource;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by rickzx98 on 8/31/15.
 */
@Path("services/v2/user/group")
@Stateless
public class UserGroupService extends PageResource<FlowUserGroup, Long> {

    public UserGroupService() {
        super(FlowUserGroup.class);
    }

    @Path("by-name")
    @GET
    public FlowUserGroup getByName(@QueryParam("name") String name) {
        FlowUserGroup group = null;

        List<FlowUserGroup> flowUserGroupList = getEntityManager().createNamedQuery(FlowUserGroup.FIND_GROUP_BY_NAME, FlowUserGroup.class).setParameter("groupName", name).getResultList();

        if (flowUserGroupList != null && !flowUserGroupList.isEmpty()) {
            group = flowUserGroupList.get(0);
        }

        return group;
    }


}
