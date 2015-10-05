package com.jsofttechnologies.report.utlil;

import java.lang.reflect.Field;

/**
 * Created by jerico on 10/6/15.
 */
public class DefaultReportConverter implements ReportConverter {


    @Override
    public Object getValue(Object entity, Field field, Object value) {
        return value;
    }
}
