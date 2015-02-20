/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsofttechnologies.security;


import com.jsofttechnologies.jpa.admin.FlowSession;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserProfile;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jerico
 */
public class MergeSecurityContext implements SecurityContext {

    private final FlowUser flowUser;
    private final FlowSession flowSession;
    private final Logger logger;

    public MergeSecurityContext(FlowUser flowUser, FlowSession flowSession) {
        logger = Logger.getLogger(getClass().getName());
        this.flowUser = flowUser;
        this.flowSession = flowSession;
    }

    @Override
    public Principal getUserPrincipal() {
        return flowUser;
    }

    @Override
    public boolean isUserInRole(String role) {
        logger.log(Level.INFO, "isUserInRole: " + role);
        if (null == flowSession || !flowSession.getActive()) {
            //Forbidden
            Response denied = Response.status(Response.Status.FORBIDDEN).entity("Permission Denied").build();
            throw new WebApplicationException(denied);
        }

        try {
            FlowUserProfile flowUserProfile = new FlowUserProfile(role);
            logger.log(Level.INFO, "isUserInRole: " + role + " accepted!");
            return flowUser.getFlowUserProfileSet().contains(flowUserProfile);
        } catch (Exception e) {

        }

        return false;

    }

    @Override
    public boolean isSecure() {
        return (null != flowSession) ? flowSession.getSecured() : false;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }

    @Override
    public String toString() {
        return "MergeSecurityContext{" + "mergeUser=" + flowUser + ", mergeSession=" + flowSession + "}";
    }

}
