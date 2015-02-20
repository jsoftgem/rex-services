package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Path("services/merge_user_query")
public class FlowUserQueryService extends QueryService<FlowUser> {

    /**
     *
     */
    private static final long serialVersionUID = -1985993914358013730L;

    private MergeExceptionSummary mergeExceptionSummary;


    public FlowUserQueryService() {
        super(FlowUser.class, FlowUser.FIND_ALL);
    }

    @GET
    @Path("find_by_id/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public FlowUser getFlowUserById(@PathParam("id") Long id) {
        setNamedQuery(FlowUser.FIND_BY_ID);
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        setParam(param);
        return getSingleResult();
    }


    public FlowUser getFlowUserByUsername(String username) {
        try {
            setNamedQuery(FlowUser.FIND_BY_USERNAME);
            putParam("username", username);
            List<FlowUser> userList = doGetResultList();
            if (userList != null && !userList.isEmpty()) {
                return userList.get(0);
            }
        } catch (Exception e) {
            mergeExceptionSummary.handleException(e, getClass());
        }

        return null;
    }

    public FlowUser getFlowUserByEmail(String email) {
        try {
            setNamedQuery(FlowUser.FIND_BY_EMAIL);
            putParam("email", email);
            List<FlowUser> userList = doGetResultList();
            if (userList != null && !userList.isEmpty()) {
                return userList.get(0);
            }
        } catch (Exception e) {
            mergeExceptionSummary.handleException(e, getClass());
        }

        return null;
    }
}
