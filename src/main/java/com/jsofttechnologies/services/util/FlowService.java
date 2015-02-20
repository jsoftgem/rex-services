package com.jsofttechnologies.services.util;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.util.ProjectConstants;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

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

}
