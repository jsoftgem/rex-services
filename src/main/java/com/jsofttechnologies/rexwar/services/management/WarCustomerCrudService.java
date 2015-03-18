package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.model.management.WarCustomer;
import com.jsofttechnologies.rexwar.model.tables.School;
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

        String authorization = request.getHeader("Authorization");
        FlowSessionHelper.Promise promise = sessionHelper.isAuthorized(authorization);

        if (warCustomer.getCustomerCode() == null || warCustomer.getCustomerCode().isEmpty()) {
            throw throwException("WAR_CUSTOMER_NULL_CUSTOMER_CODE");
        }

        if (warCustomer.getSchool() == null) {
            throw throwException("WAR_CUSTOMER_NULL_SCHOOL");
        } else {
            School school = warCustomer.getSchool();
            if (school.getAddressLine1() == null || school.getAddressLine1().isEmpty()) {
                throw throwException("WAR_SCHOOL_NULL_ADDRESS");
            }

          /*  if (school.getRegion() == null) {
                throw throwException("WAR_SCHOOL_NULL_REGION");
            }*/

            if (school.getEmail() == null || school.getEmail().isEmpty()) {
                throw throwException("WAR_SCHOOL_NULL_EMAIL");
            }

            if (school.getLandline() == null || school.getLandline().isEmpty()) {
                throw throwException("WAR_SCHOOL_NULL_LANDLINE");
            }

        }

       /* if (warCustomer.getBuyingProcess() == null) {
            throw throwException("WAR_CUSTOMER_NULL_BUYING_PROCESS");
        }
*/

        if (promise.getOk()) {

            FlowUser flowUser = promise.getFlowUser();

            warCustomer.setCreatedByAgentId(flowUser.getId());
        }

        return warCustomer;
    }

    @Override
    protected WarCustomer preUpdateValidation(WarCustomer warCustomer) throws Exception {

        String authorization = request.getHeader("Authorization");
        FlowSessionHelper.Promise promise = sessionHelper.isAuthorized(authorization);

        if (warCustomer.getCustomerCode() == null || warCustomer.getCustomerCode().isEmpty()) {
            throw throwException("WAR_CUSTOMER_NULL_CUSTOMER_CODE");
        }

        if (warCustomer.getSchool() == null) {
            throw throwException("WAR_CUSTOMER_NULL_SCHOOL");
        } else {
            School school = warCustomer.getSchool();
            if (school.getAddressLine1() == null || school.getAddressLine1().isEmpty()) {
                throw throwException("WAR_SCHOOL_NULL_ADDRESS");
            }

            /*if (school.getRegion() == null) {
                throw throwException("WAR_SCHOOL_NULL_REGION");
            }*/

            if (school.getEmail() == null || school.getEmail().isEmpty()) {
                throw throwException("WAR_SCHOOL_NULL_EMAIL");
            }

            if (school.getLandline() == null || school.getLandline().isEmpty()) {
                throw throwException("WAR_SCHOOL_NULL_LANDLINE");
            }

        }
/*
        if (warCustomer.getBuyingProcess() == null) {
            throw throwException("WAR_CUSTOMER_NULL_BUYING_PROCESS");
        }*/


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
