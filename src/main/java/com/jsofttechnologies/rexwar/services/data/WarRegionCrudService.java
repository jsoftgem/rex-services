package com.jsofttechnologies.rexwar.services.data;

import com.jsofttechnologies.rexwar.model.management.WarCustomerRegion;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 1/13/2015.
 */
@Path("services/war/region_crud")
@Stateless
public class WarRegionCrudService extends CrudService<WarCustomerRegion,Long> {

    public WarRegionCrudService(){
        super(WarCustomerRegion.class);
    }

    @Override
    protected WarCustomerRegion preCreateValidation(WarCustomerRegion warCustomerRegion) throws Exception {
        return warCustomerRegion;
    }

    @Override
    protected WarCustomerRegion preUpdateValidation(WarCustomerRegion warCustomerRegion) throws Exception {
        return warCustomerRegion;
    }
}
