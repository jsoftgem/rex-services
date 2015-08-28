package com.jsofttechnologies.report.utlil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Jerico on 4/6/2015.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportColumn {
    String name() default "";

    String style() default "font:inherit;color:black;";

    String align() default "center";

    String headerStyle() default "font:inherit;color:black;background:lightblue;";

    String field() default "";

    int colSpan() default 1;
}
