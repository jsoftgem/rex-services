package com.jsofttechnologies.rexwar.services.data;

import com.jsofttechnologies.rexwar.model.tables.Level;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by Jerico on 1/20/2015.
 */
@Path("services/war/level_query")
@Stateless
public class WarLevelQueryService extends QueryService<Level> {

    public WarLevelQueryService() {
        super(Level.class, Level.FIND_ALL);
    }
}
