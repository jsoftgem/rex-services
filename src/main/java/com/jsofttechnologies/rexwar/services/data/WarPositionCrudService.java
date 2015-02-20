package com.jsofttechnologies.rexwar.services.data;

import com.jsofttechnologies.rexwar.model.tables.Position;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 1/19/2015.
 */
@Path("services/war/position_crud")
@Stateless
public class WarPositionCrudService extends CrudService<Position, Long> {

    public WarPositionCrudService() {
        super(Position.class);
    }

    @Override
    protected Position preCreateValidation(Position position) throws Exception {
        return position;
    }

    @Override
    protected Position preUpdateValidation(Position position) throws Exception {
        return position;
    }
}
