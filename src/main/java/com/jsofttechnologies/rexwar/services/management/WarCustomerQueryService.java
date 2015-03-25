package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.rexwar.model.management.WarCustomer;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by Jerico on 1/13/2015.
 */
@Path("services/war/customer_query")
@Stateless
public class WarCustomerQueryService extends QueryService<WarCustomer> {

    public WarCustomerQueryService() {
        super(WarCustomer.class, WarCustomer.FIND_ALL);
    }


    public WarCustomer findByCustomerCode(String customerCode) {
        List<WarCustomer> customers = null;
        try {
            setNamedQuery(WarCustomer.FIND_BY_CUSTOMER_CODE);
            putParam("customerCode", customerCode);
            customers = doGetResultList();
            if (customers != null && !customers.isEmpty()) {
                return customers.get(0);
            }

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), customerCode);
        }


        return null;

    }

    public Boolean isExists(String customerCode) {
        WarCustomer customer = findByCustomerCode(customerCode);
        return customer != null;
    }


}
