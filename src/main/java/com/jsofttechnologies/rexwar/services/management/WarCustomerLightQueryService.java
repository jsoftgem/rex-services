package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.rexwar.model.management.WarCustomerLight;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 1/28/2015.
 */
@Path("services/war/customer_light_query")
@Stateless
public class WarCustomerLightQueryService extends QueryService<WarCustomerLight> {
    public WarCustomerLightQueryService() {
        super(WarCustomerLight.class, WarCustomerLight.FIND_ALL);
    }
}
