package com.jsofttechnologies.v2.services.admin;

import com.jsofttechnologies.jpa.dev.FlowGroup;
import com.jsofttechnologies.v2.services.resource.PageResource;

import javax.ejb.Stateless;
import javax.faces.flow.Flow;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.security.acl.Group;
import java.util.List;

/**
 * Created by rickzx98 on 8/31/15.
 */
@Path("services/v2/group")
@Stateless
public class GroupService extends PageResource<FlowGroup, Long> {

    public GroupService() {
        super(FlowGroup.class);
    }

    @GET
    @Path("by-name")
    public FlowGroup getByName(@QueryParam("name") String name) {
        FlowGroup group = null;

        List<FlowGroup> flowGroupList = getEntityManager().createNamedQuery(FlowGroup.FIND_BY_GROUP_NAME, FlowGroup.class).setParameter("name", name).getResultList();

        if (flowGroupList != null && !flowGroupList.isEmpty()) {
            group = flowGroupList.get(0);
        }


        return group;
    }


}
