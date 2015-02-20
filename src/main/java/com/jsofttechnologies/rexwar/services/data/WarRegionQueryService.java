package com.jsofttechnologies.rexwar.services.data;

import com.jsofttechnologies.interceptor.Notify;
import com.jsofttechnologies.jpa.util.FlowAlertType;
import com.jsofttechnologies.rexwar.model.tables.Region;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 1/13/2015.
 */
@Path("services/war/region_query")
@Stateless
@Notify(task = "region_task", alertType = FlowAlertType.NOTICE, page = "region_edit")
public class WarRegionQueryService extends QueryService<Region> {

    public WarRegionQueryService() {
        super(Region.class, Region.FIND_ALL);
    }
}
