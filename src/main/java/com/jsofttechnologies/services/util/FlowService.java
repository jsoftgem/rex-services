package com.jsofttechnologies.services.util;

import com.jsofttechnologies.ds.StoredProcedures;
import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.util.PasswordHash;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.v2.util.Constants;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jerico on 6/13/2014.
 */
public abstract class FlowService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7893738438250121432L;
    @Context
    protected HttpSession httpSession;

    @Context
    protected ServletContext context;

    @Context
    protected HttpServletRequest request;

    @EJB
    protected MessageService messageService;

    @EJB
    protected MergeExceptionSummary exceptionSummary;

    @EJB
    protected StoredProcedures storedProcedures;

    @EJB
    protected FlowSessionHelper session;

    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    protected EntityManager currentEntityManager;

    private final static Map<String, Object> serviceCache = new HashMap<>();

    protected String getAuthorization() {
        return request.getHeader(ProjectConstants.HEADER_AUTHORIZATION);
    }

    protected FlowSessionHelper.Promise getUserSession() {
        return session.isAuthorized(getAuthorization());
    }


    @GET
    @PermitAll
    @Path("/getHashedPassword")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHashed(@QueryParam("password") String password) {
        try {
            return PasswordHash.createHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @PermitAll
    @Path("/info")
    @Produces("text/plain")
    public String info() {
        return new StringBuilder(ProjectConstants.APP_NAME).append(" ").append("v").append(ProjectConstants.APP_VERSION).append("\n")
                .append(getClass().getName()).append("\n").append("Service v").append(ProjectConstants.SERVICE_VERSION).append("\ncontext path: ").append(context.getContextPath()).toString();
    }

    protected String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {

                String[] name = filename.split("=");

                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

    //save to somewhere
    protected boolean writeFile(byte[] content, File file) throws IOException {

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileOutputStream fop = new FileOutputStream(file)) {
                fop.write(content);
                fop.flush();
            }

            return true;
        } catch (IOException e) {
        }
        return false;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }


    public <T> T serviceMap(Object key, Object o, Class<T> type, boolean isNew) {
        if (key == null) return null;
        String theKey = key.toString().trim().toLowerCase() + type.getName().toLowerCase();
        if (!serviceCache.containsKey(theKey) || isNew) {
            serviceCache.put(theKey, o);
        }
        return (T) serviceCache.get(theKey);
    }


    public <T> boolean isCached(Object key, Class<T> type) {
        if (key == null) return false;
        String theKey = key.toString().trim().toLowerCase() + type.getName().toLowerCase();
        return serviceCache.containsKey(theKey);
    }


    protected Boolean isDevelopmentStage() {
        return context.getInitParameter(Constants.PROJECT_STAGE).equalsIgnoreCase(Constants.STAGE_DEVELOPMENT);
    }


    protected Boolean isProducitonStage() {
        return context.getInitParameter(Constants.PROJECT_STAGE).equalsIgnoreCase(Constants.STAGE_PRODUCTION);
    }


}
