package com.jsofttechnologies.v2.services;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.services.util.MessageService;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jerico on 7/24/2015.
 */
public abstract class FluidPlatformService {

    @Context
    protected ServletContext servlet;
    @EJB
    protected MessageService messageService;
    @EJB
    protected MergeExceptionSummary ejbExceptionHandler;

    @Context
    protected HttpServletRequest request;
    @Context
    protected ServletContext context;

    @Path("/info")
    @GET
    public String version() {
        return "service class:" + this.getClass().getName() + " version: " + servlet.getInitParameter("SERVICE_VERSION");
    }

    protected final Map<String, Object> serviceCache = new HashMap<>();

    protected <T> T serviceMap(Object key, Object o, Class<T> type, boolean isNew) {
        if (key == null) return null;
        String theKey = key.toString().trim().toLowerCase() + type.getName().toLowerCase();
        if (!serviceCache.containsKey(theKey) || isNew) {
            serviceCache.put(theKey, o);
        }
        return (T) serviceCache.get(theKey);
    }


    protected <T> boolean isCached(Object key, Class<T> type) {
        if (key == null) return false;
        String theKey = key.toString().trim().toLowerCase() + type.getName().toLowerCase();
        return serviceCache.containsKey(theKey);
    }
}
