package com.jsofttechnologies.rexwar.services.data;

import com.jsofttechnologies.rexwar.model.tables.Level;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 1/20/2015.
 */
@Path("services/war/level_crud")
@Stateless
public class WarLevelCrudService extends CrudService<Level, Long> {

    public WarLevelCrudService() {
        super(Level.class);
    }

    @Override
    protected Level preCreateValidation(Level level) throws Exception {
        return level;
    }

    @Override
    protected Level preUpdateValidation(Level level) throws Exception {
        return level;
    }
}
