package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.model.management.WarCustomer;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.util.CrudService;
import com.jsofttechnologies.services.util.FlowSessionHelper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

/**
 * Created by Jerico on 1/13/2015.
 */
@Path("services/war/customer_crud")
@Stateless
public class WarCustomerCrudService extends CrudService<WarCustomer, Long> {

    @Context
    private HttpServletRequest request;


    @EJB
    private FlowSessionHelper sessionHelper;

    @EJB
    private WarAgentQueryService warAgentQueryService;

    public WarCustomerCrudService() {
        super(WarCustomer.class);
    }


    @Override
    protected WarCustomer preCreateValidation(WarCustomer warCustomer) throws Exception {
        String authorization = null;
        if (isCached("migration", String.class)) {
            authorization = serviceMap("migration", null, String.class, false);
        } else {
            authorization = request.getHeader("Authorization");
        }
        FlowSessionHelper.Promise promise = sessionHelper.isAuthorized(authorization);

        if (warCustomer.getCustomerCode() == null || warCustomer.getCustomerCode().isEmpty()) {
            throw throwException("WAR_CUSTOMER_NULL_CUSTOMER_CODE");
        }

        if (warCustomer.getSchool() == null) {
            throw throwException("WAR_CUSTOMER_NULL_SCHOOL");
        }
        if (warCustomer.getOwnerAgentId() != null) {
            WarAgent warAgent = warAgentQueryService.getById(warCustomer.getOwnerAgentId());
            warCustomer.setRegionCode(warAgent.getRegion());
        }

        if (promise.getOk()) {

            FlowUser flowUser = promise.getFlowUser();

            warCustomer.setCreatedByAgentId(flowUser.getId());
        }
        return warCustomer;
    }

    @Override
    protected WarCustomer preUpdateValidation(WarCustomer warCustomer) throws Exception {

        String authorization = null;

        if (isCached("migration", String.class)) {
            authorization = serviceMap("migration", null, String.class, false);
        } else {
            authorization = request.getHeader("Authorization");
        }


        FlowSessionHelper.Promise promise = sessionHelper.isAuthorized(authorization);

        if (warCustomer.getCustomerCode() == null || warCustomer.getCustomerCode().isEmpty()) {
            throw throwException("WAR_CUSTOMER_NULL_CUSTOMER_CODE");
        }

        if (warCustomer.getSchool() == null) {
            throw throwException("WAR_CUSTOMER_NULL_SCHOOL");
        }
        if (warCustomer.getOwnerAgentId() != null) {
            WarAgent warAgent = warAgentQueryService.getById(warCustomer.getOwnerAgentId());
            warCustomer.setRegionCode(warAgent.getRegion());
        }

        if (promise.getOk()) {

            FlowUser flowUser = promise.getFlowUser();

            FlowUserGroup flowUserGroup = promise.getFlowUserGroup();

            if (flowUserGroup.getGroupName().equals(WarConstants.AGENT_GROUP)) {
                WarAgent warAgent = warAgentQueryService.findAgentByUsername(flowUser.getUsername());
                if (warAgent.getId() != warCustomer.getOwnerAgentId()) {
                    throw throwException("WAR_CUSTOMER_AGENT_NOT_OWNER");
                }
            }

        }


        return warCustomer;
    }
}
