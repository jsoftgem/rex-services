/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsofttechnologies.rexwar.services.data;

import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.model.tables.School;
import com.jsofttechnologies.rexwar.services.management.WarAgentQueryService;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Jerico
 */
@Path("services/war/school_query")
@Stateless
public class WarSchoolQueryService extends QueryService<School> {

    private WarAgentQueryService warAgentQueryService;

    public WarSchoolQueryService() {
        super(School.class, School.FIND_ALL);
    }


    public School findBySchoolName(String name) {
        List<School> schoolList = null;

        try {
            setNamedQuery(School.FIND_BY_SCHOOL_NAME);
            putParam("name", name);
            schoolList = doGetResultList();
            if (schoolList != null && !schoolList.isEmpty()) {
                return schoolList.get(0);
            }

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), name);
        }


        return null;
    }

    /*TODO: findSchoolPerUserLevel*/
    @GET
    @Path("find_school_per_user_level")
    public Response findSchoolPerUserLevel(@HeaderParam("Authorization") String authorization) {
        Response response = null;
        try {

            FlowSessionHelper.Promise authorized = session.isAuthorized(authorization);

            if (authorized.getOk()) {

                FlowUserGroup flowUserGroup = authorized.getFlowUserGroup();

                switch (flowUserGroup.getGroupName()) {


                    case WarConstants.AGENT_GENERAL_MANAGER_GROUP:
                        setNamedQuery(School.FIND_ALL);
                        response = Response.ok(doGetResultList(), MediaType.APPLICATION_JSON_TYPE).build();
                        break;
                    case WarConstants.AGENT_GROUP:
                    case WarConstants.AGENT_REGIONAL_MANAGER_GROUP:
                        WarAgent warAgent = warAgentQueryService.findAgentByUsername(authorized.getFlowUser().getUsername());
                        setNamedQuery(WarAgent.FIND_BY_REGION);
                        putParam("region", warAgent.getRegion());
                        response = Response.ok(doGetResultList(), MediaType.APPLICATION_JSON_TYPE).build();
                        break;
                    default:
                        setNamedQuery(School.FIND_ALL);
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

}
