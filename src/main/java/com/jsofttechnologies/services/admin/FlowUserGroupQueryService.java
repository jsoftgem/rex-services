package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerico on 11/2/2014.
 */
@Path("services/flow_user_group_query")
@Stateless
public class FlowUserGroupQueryService extends QueryService<FlowUserGroup> {
    @EJB
    private MergeExceptionSummary exceptionSummary;

    public FlowUserGroupQueryService() {
        super(FlowUserGroup.class, FlowUserGroup.FIND_ALL);
    }


    @GET
    @Path("/getInstanceByUserId/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public FlowUserGroup findGroupByUserId(@PathParam("id") Long id) {
        setNamedQuery(FlowUserGroup.FIND_GROUP_BY_USER_ID);
        Map<String, Object> param = new HashMap<>();
        param.put("userId", id);
        setParam(param);
        return getSingleResult();
    }

    public FlowUserGroup findGroupByName(String group) {
        try {
            setNamedQuery(FlowUserGroup.FIND_GROUP_BY_NAME);
            putParam("groupName", group);
            List<FlowUserGroup> flowUserGroupList = doGetResultList();
            if (flowUserGroupList != null && !flowUserGroupList.isEmpty()) {
                return flowUserGroupList.get(0);
            }
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }
        return null;
    }

}
