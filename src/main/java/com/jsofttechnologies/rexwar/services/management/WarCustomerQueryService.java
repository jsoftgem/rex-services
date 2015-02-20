package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.rexwar.model.management.WarCustomer;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 1/13/2015.
 */
@Path("services/war/customer_query")
@Stateless
public class WarCustomerQueryService extends QueryService<WarCustomer> {

    public WarCustomerQueryService() {
        super(WarCustomer.class, WarCustomer.FIND_ALL);
    }

}
