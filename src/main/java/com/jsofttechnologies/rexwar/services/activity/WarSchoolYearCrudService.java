package com.jsofttechnologies.rexwar.services.activity;

import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.rexwar.model.activty.WarCustomerMarket;
import com.jsofttechnologies.rexwar.model.activty.WarMarketSegment;
import com.jsofttechnologies.rexwar.model.activty.WarSchoolYear;
import com.jsofttechnologies.services.util.CrudService;
import com.jsofttechnologies.services.util.FlowSessionHelper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.Set;

/**
 * Created by Jerico on 1/28/2015.
 */
@Path("services/war/school_year_crud")
@Stateless
public class WarSchoolYearCrudService extends CrudService<WarSchoolYear, Long> {

    @Context
    private HttpServletRequest request;
    @EJB
    private FlowSessionHelper flowSessionHelper;

    public WarSchoolYearCrudService() {
        super(WarSchoolYear.class);
    }


    @Override
    protected WarSchoolYear preCreateValidation(WarSchoolYear warSchoolYear) throws Exception {
        String authorization = request.getHeader("Authorization");
        FlowSessionHelper.Promise promise = flowSessionHelper.isAuthorized(authorization);


        if (promise.getOk()) {
            FlowUser flowUser = promise.getFlowUser();
            warSchoolYear.setCreateByUserId(flowUser.getId());


            Set<WarCustomerMarket> customerMarketList = warSchoolYear.getWarCustomerMarkets();

            for (WarCustomerMarket customerMarket : customerMarketList) {
                customerMarket.setSchoolYear(warSchoolYear);
            }


            Set<WarMarketSegment> warMarketSegments = warSchoolYear.getWarMarketSegments();

            for (WarMarketSegment segment : warMarketSegments) {
                segment.setSchoolYear(warSchoolYear);
            }

        }

        return warSchoolYear;
    }

    @Override
    protected WarSchoolYear preUpdateValidation(WarSchoolYear warSchoolYear) throws Exception {

        Set<WarCustomerMarket> customerMarketList = warSchoolYear.getWarCustomerMarkets();

        customerMarketList.stream().filter(customerMarket -> customerMarket.getSchoolYear() == null).forEach(customerMarket -> customerMarket.setSchoolYear(warSchoolYear));

        return warSchoolYear;
    }
}
