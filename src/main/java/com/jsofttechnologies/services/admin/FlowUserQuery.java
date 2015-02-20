/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsofttechnologies.services.admin;

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jerico
 */
@Path("services/flow_user_query")
@Stateless
public class FlowUserQuery extends QueryService<FlowUser> {

    public FlowUserQuery() {
        super(FlowUser.class, FlowUser.FIND_ALL);
    }


    @GET
    @Path("find_by_username/{username}")
    @Produces("application/json")
    public FlowUser findByUsername(@PathParam("username") String username) {
        Logger logger = Logger.getLogger(FlowUserQuery.class.getName());

        logger.log(Level.INFO, "username: " + username);

        setNamedQuery(FlowUser.FIND_BY_USERNAME);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", username);

        setParam(paramMap);


        List<FlowUser> userList = doGetResultList();
        boolean log = false;
        if (log = (userList != null && !userList.isEmpty())) {
            return userList.get(0);
        }
        logger.log(Level.INFO, "found: " + (log ? "one" : "none"));


        return null;
    }
}
