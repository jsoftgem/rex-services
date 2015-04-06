package com.jsofttechnologies.report.processor;

import com.jsofttechnologies.report.utlil.Report;
import com.jsofttechnologies.report.utlil.ReportHeader;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Jerico on 4/6/2015.
 */

@Report
@Provider
public class ReportWriter implements WriterInterceptor {
    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        Logger logger = Logger.getLogger(ReportWriter.class.getName());
        Report report = null;
        for (Annotation annotation : context.getAnnotations()) {
            if (annotation instanceof Report) {
                report = (Report) annotation;
                break;
            }
        }
        if (report == null) {
            context.proceed();
            return;
        }


        Object entity = context.getEntity();

        if (entity.getClass().isArray() || entity instanceof Collection) {
            List<Object> list = null;
            if (entity.getClass().isArray()) {
                Object[] obs = (Object[]) entity;
                list = Arrays.asList(obs);
            } else if (entity instanceof Collection) {
                list = (List) entity;
            }
            Map<String, Map<String, List<String>>> reportMap = new HashMap<>();

            for (Object en : list) {
                Class cls = en.getClass();
                Map<String, List<String>> reportContent = null;
                Annotation reportHeader = cls.getDeclaredAnnotation(ReportHeader.class);
                if (reportHeader != null) {
                    if (reportMap.containsKey(((ReportHeader) reportHeader).name())) {
                        reportContent = reportMap.get(((ReportHeader) reportHeader).name());
                    } else {
                        reportContent = new HashMap<>();
                        reportMap.put(((ReportHeader) reportHeader).name(), reportContent);
                    }
                } else {
                    continue;
                }


            }


        }

        context.proceed();
    }
}
