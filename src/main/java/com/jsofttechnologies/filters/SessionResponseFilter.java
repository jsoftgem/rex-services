/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsofttechnologies.filters;

import com.jsofttechnologies.jpa.util.FlowJpe;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jerico
 */
@Provider
public class SessionResponseFilter implements ContainerResponseFilter {

    @SuppressWarnings("rawtypes")
    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
       /* Logger logger = Logger.getLogger(SessionResponseFilter.class.getName());
        logger.info("sessions-response-filter");

        MultivaluedMap headerMap = requestContext.getHeaders();
        headerMap.putSingle("Access-Control-Allow-Headers", "*");
        for (Object key : headerMap.keySet()) {
            logger.log(Level.INFO, "header: {0}={1}", new Object[]{key,
                    headerMap.get(key)});
        }*/

    }

}
