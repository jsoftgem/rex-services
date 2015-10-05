package com.jsofttechnologies.report.utlil;

import java.lang.reflect.Field;

/**
 * Created by jerico on 10/6/15.
 */
public interface ReportConverter<T> {

    public Object getValue(T entity, Field field, Object value);


}
