package com.jsofttechnologies.rexwar.services.data;

import com.jsofttechnologies.rexwar.model.tables.Position;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by Jerico on 1/19/2015.
 */
@Path("services/war/position_query")
@Stateless
public class WarPositionQueryService extends QueryService<Position> {
    public WarPositionQueryService() {
        super(Position.class, Position.FIND_ALL);
    }

    @GET
    @Path("/position_name")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPositionName(@QueryParam("id") Long id) {
        Position position = getById(id);
        if (position != null) {
            return "<span>" + position.getDescription() + "</span>";
        }
        return "<span>none</span>";
    }
}
