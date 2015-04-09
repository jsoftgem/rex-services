package com.jsofttechnologies.report.interceptor;

import com.jsofttechnologies.report.generator.ReportGenerator;
import com.jsofttechnologies.report.utlil.Report;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Jerico on 4/7/2015.
 */
@Provider
@Report
public class ReportInterceptor implements WriterInterceptor {
    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {

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


        try {

            ReportGenerator reportGenerator = (ReportGenerator) report.generator().getDeclaredMethod("getInstance", null).invoke(null, null);

            Object filteredEntity = reportGenerator.generate(entity);

            context.setEntity(filteredEntity);

            String[] mediaType = reportGenerator.getContentType().split("/");

            context.setMediaType(new MediaType(mediaType[0], mediaType[1]));

            context.setType(reportGenerator.getType());

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        context.proceed();
    }

}
