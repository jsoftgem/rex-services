package com.jsofttechnologies.rexwar.services.activity;

import com.jsofttechnologies.rexwar.model.activity.WarPlanner;
import com.jsofttechnologies.rexwar.model.activity.WarSchoolYear;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 1/31/2015.
 */
@Path("services/war/planner_crud")
@Stateless
public class WarPlannerCrudService extends CrudService<WarPlanner, Long> {

    @EJB
    private WarSchoolYearQueryService schoolYearQueryService;

    public WarPlannerCrudService() {
        super(WarPlanner.class);
    }


    @Override
    protected WarPlanner preCreateValidation(WarPlanner warPlanner) throws Exception {
        if (warPlanner.getSchoolYear() == null) {
            throw throwException("WAR_PLANNER_NULL_SCHOOL_YEAR");
        } else {
            WarSchoolYear schoolYear = schoolYearQueryService.getById(warPlanner.getSchoolYear());
            if (warPlanner.getYear() < schoolYear.getPeriodYear()) {
                throw throwException("WAR_PLANNER_OUT_OF_RANGE");
            } else if (warPlanner.getYear() > schoolYear.getPeriodYearTo()) {
                throw throwException("WAR_PLANNER_OUT_OF_RANGE");
            }
        }

        return warPlanner;
    }

    @Override
    protected WarPlanner preUpdateValidation(WarPlanner warPlanner) throws Exception {
        return warPlanner;
    }


    @Override
    public String createSuccessMessage() {
        return getMessage("WAR_PLANNER_SUBMITTED");
    }

}
