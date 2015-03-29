package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.rexwar.services.management.WarAgentQueryService;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.services.util.QueryService;
import com.jsofttechnologies.util.ProjectConstants;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @EJB
    private WarAgentQueryService warAgentQueryService;

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

    @GET
    @Path("/find_by_group_by_user_level")
    public Response findGroupByUserLevel(@HeaderParam("Authorization") String authorization) {
        Response response = null;
        try {

            FlowSessionHelper.Promise authorized = session.isAuthorized(authorization);

            if (authorized.getOk()) {

                FlowUserGroup flowUserGroup = authorized.getFlowUserGroup();

                switch (flowUserGroup.getGroupName()) {
                    case WarConstants.WAR_ADMIN_GROUP:
                        setNamedQuery(FlowUserGroup.FIND_GROUP_NOT_ADMIN);
                        response = Response.ok(doGetResultList(), MediaType.APPLICATION_JSON_TYPE).build();
                        break;
                    default:
                        setNamedQuery(FlowUserGroup.FIND_ALL);
                        response = Response.ok(doGetResultList(), MediaType.APPLICATION_JSON_TYPE).build();
                        break;
                }


            } else {
                response = authorized.getResponse();
            }


        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), authorization);
        }

        return response;
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
