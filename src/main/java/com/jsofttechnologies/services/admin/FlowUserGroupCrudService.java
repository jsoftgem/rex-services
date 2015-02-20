package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.Set;

/**
 * Created by Jerico on 11/2/2014.
 */
@Path("services/flow_user_group_crud")
@Stateless
public class FlowUserGroupCrudService extends CrudService<FlowUserGroup, Long> {

    public FlowUserGroupCrudService() {
        super(FlowUserGroup.class);
    }

    @EJB
    private FlowUserQueryService flowUserQueryService;

    @Override
    protected FlowUserGroup preCreateValidation(FlowUserGroup flowUserGroup) throws Exception {
        if (flowUserGroup.getOwnerUserId() != null) {

            FlowUser flowUser = flowUserQueryService.getFlowUserById(flowUserGroup.getOwnerUserId());
            flowUserGroup.setGroupAdminFullname(flowUser.getFlowUserDetail().getFullName());

        }
        return flowUserGroup;
    }

    @Override
    protected FlowUserGroup preUpdateValidation(FlowUserGroup flowUserGroup) throws Exception {
        Set<FlowUser> flowUsers = flowUserGroup.getFlowUsers();

        for (FlowUser flowUser : flowUsers) {
            if (flowUser.getFlowUserGroup() == null) {
                flowUser.setFlowUserGroup(flowUserGroup);
            }
        }

        if (flowUserGroup.getOwnerUserId() != null) {

            FlowUser flowUser = flowUserQueryService.getFlowUserById(flowUserGroup.getOwnerUserId());
            flowUserGroup.setGroupAdminFullname(flowUser.getFlowUserDetail().getFullName());

        }


        return flowUserGroup;
    }
}
