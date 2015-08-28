package com.jsofttechnologies.services.util;

import com.jsofttechnologies.ejb.MergeExceptionSummary;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * Created by Jerico on 12/1/2014.
 */
@Path("factories/messages")
@Stateless
public class MessageService extends FlowService {

    @EJB
    private MergeExceptionSummary exceptionSummary;

    private static Properties properties;


    @PermitAll
    @GET
    @Path("/")
    @Produces("text/plain")
    public String getMessage(@QueryParam("key") String key) {

        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(context.getResource("/WEB-INF/messages.properties").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return properties.getProperty(key);
    }


    @PermitAll
    @GET
    @Path("/")
    @Produces("text/plain")
    public String getMessage(@QueryParam("key") String key, @QueryParam("param") String... param) {

        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(context.getResource("/WEB-INF/messages.properties").openStream());
            } catch (IOException e) {
                exceptionSummary.handleException(e, getClass());
            }
        }

        if (key != null) {
            String property = properties.getProperty(key);


            if (param != null) {
                return MessageFormat.format(property, param);
            }


            return property;
        }

        return null;
    }

}
