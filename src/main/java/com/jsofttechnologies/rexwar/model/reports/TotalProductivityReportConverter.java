package com.jsofttechnologies.rexwar.model.reports;

import com.jsofttechnologies.report.utlil.ReportConverter;

import java.lang.reflect.Field;

/**
 * Created by jerico on 10/6/15.
 */
public class TotalProductivityReportConverter implements ReportConverter<WarReportWeeklyAgentView> {


    @Override
    public Object getValue(WarReportWeeklyAgentView entity, Field field, Object value) {
        Integer planned = entity.getPlannedActual();
        Integer unplanned = entity.getUnplannedActual();
        Integer target = entity.getPlannedTarget();
        if (target > 0) {
            Integer total = ((100 * (planned + unplanned)) / target);
            if (total > 125) {
                total = 125;
            }
            return total + "%";
        }

        return "0%";

    }
}
