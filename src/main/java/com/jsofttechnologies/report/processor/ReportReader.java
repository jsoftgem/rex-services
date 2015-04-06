package com.jsofttechnologies.report.processor;

import com.jsofttechnologies.report.utlil.Report;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jerico on 4/6/2015.
 */
@Report
@Provider
public class ReportReader implements ReaderInterceptor {
    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        Logger logger = Logger.getLogger(ReportWriter.class.getName());
        logger.info("report-reader");
        logger.log(Level.INFO, "report-input", context.getInputStream());
        logger.log(Level.INFO, "report-media-type", context.getMediaType());
        logger.log(Level.INFO, "report-headers", context.getHeaders());
        logger.log(Level.INFO, "report-annotations", context.getAnnotations());
        logger.info("report-reader-end");
        return context.proceed();
    }
}
