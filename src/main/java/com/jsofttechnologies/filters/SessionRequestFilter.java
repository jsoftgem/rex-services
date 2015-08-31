/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsofttechnologies.filters;

import com.jsofttechnologies.security.MergeSecurityContext;
import com.jsofttechnologies.security.SessionHelper;
import com.jsofttechnologies.util.ProjectConstants;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jerico
 */
//@Provider
public class SessionRequestFilter implements ContainerRequestFilter {


    @EJB
    private SessionHelper sessionHelper;

    @SuppressWarnings("rawtypes")
    @Override
    public void filter(ContainerRequestContext requestContext)
            throws IOException {
       /* Logger logger = Logger.getLogger(SessionRequestFilter.class.getName());

        logger.log(Level.INFO, "session-request-filter: url {0}",
                requestContext.getUriInfo());

        MultivaluedMap headerMap = requestContext.getHeaders();

        for (Object key : headerMap.keySet()) {
            logger.log(Level.INFO, "header: {0}={1}", new Object[]{key,
                    headerMap.get(key)});
        }

        if (headerMap.containsKey(ProjectConstants.HEADER_SESSION_TOKEN)) {
            try {
                Map<String, String> sessionMap = SessionHelper
                        .getMergeSessionMap(headerMap.get(
                                ProjectConstants.HEADER_SESSION_TOKEN)
                                .toString());
                logger.log(Level.INFO, "sessionMap {0}",
                        new Object[]{sessionMap.toString()});

                String sid = sessionMap
                        .get(ProjectConstants.KEY_HEADER_MAP_MSID);

                String userId = sessionMap.get("muid");

                logger.log(Level.INFO, "SID: {0} | UID = {1}", new Object[]{
                        sid, userId});

                MergeSecurityContext securityContext = sessionHelper
                        .getSecurityContext(userId, sid);

                requestContext.setSecurityContext(securityContext);

                logger.log(Level.INFO,
                        "requestContext security has been set: {0}",
                        new Object[]{securityContext});
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
*/
    }

}
