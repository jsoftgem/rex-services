package com.jsofttechnologies.rexwar.services.activity;

import com.jsofttechnologies.rexwar.model.activty.WarSchoolYear;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 1/28/2015.
 */
@Path("services/war/school_year_query")
@Stateless
public class WarSchoolYearQueryService extends QueryService<WarSchoolYear> {

    public WarSchoolYearQueryService() {
        super(WarSchoolYear.class, WarSchoolYear.FIND_ALL);
    }
}
