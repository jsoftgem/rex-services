package com.jsofttechnologies.report.utlil;

import com.jsofttechnologies.report.generator.HtmlReportGenerator;
import com.jsofttechnologies.report.generator.ReportGenerator;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Jerico on 4/6/2015.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Report {
    Class<?> type() default Object.class;

    Class<? extends ReportGenerator> generator() default HtmlReportGenerator.class;
}
