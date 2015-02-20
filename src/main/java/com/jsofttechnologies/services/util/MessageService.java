package com.jsofttechnologies.services.util;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Jerico on 12/1/2014.
 */
@Path("factories/messages")
@Stateless
public class MessageService extends FlowService {

    @Context
    private ServletContext context;

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
}
