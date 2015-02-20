package com.jsofttechnologies.rexwar.services.data;

import com.jsofttechnologies.rexwar.model.tables.Region;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 1/13/2015.
 */
@Path("services/war/region_crud")
@Stateless
public class WarRegionCrudService extends CrudService<Region,Long> {

    public WarRegionCrudService(){
        super(Region.class);
    }

    @Override
    protected Region preCreateValidation(Region region) throws Exception {
        return region;
    }

    @Override
    protected Region preUpdateValidation(Region region) throws Exception {
        return region;
    }
}
