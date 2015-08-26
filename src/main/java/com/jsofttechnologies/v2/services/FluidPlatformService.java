package com.jsofttechnologies.v2.services;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.services.util.MessageService;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

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

    @Path("/info")
    @GET
    public String version() {
        return "service class:" + this.getClass().getName() + " version: " + servlet.getInitParameter("service-version");
    }


}
