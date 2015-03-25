package com.jsofttechnologies.rexwar.services.data;

import com.jsofttechnologies.interceptor.Notify;
import com.jsofttechnologies.jpa.util.FlowAlertType;
import com.jsofttechnologies.rexwar.model.management.WarCustomerRegion;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by Jerico on 1/13/2015.
 */
@Path("services/war/region_query")
@Stateless
@Notify(task = "region_task", alertType = FlowAlertType.NOTICE, page = "region_edit")
public class WarRegionQueryService extends QueryService<WarCustomerRegion> {

    public WarRegionQueryService() {
        super(WarCustomerRegion.class, WarCustomerRegion.FIND_ALL);
    }

    public WarCustomerRegion findByCode(String regionCode) {
        List<WarCustomerRegion> regions = null;
        try {
            setNamedQuery(WarCustomerRegion.FIND_BY_CODE);
            putParam("regionCode", regionCode.toLowerCase());
            regions = doGetResultList();

            if (regions != null && !regions.isEmpty()) {
                return regions.get(0);
            }

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), regionCode);
        }


        return null;

    }

}
