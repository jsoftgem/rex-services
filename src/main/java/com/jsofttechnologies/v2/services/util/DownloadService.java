package com.jsofttechnologies.v2.services.util;

import com.jsofttechnologies.interceptor.UserInfoResource;
import com.jsofttechnologies.jpa.admin.FlowUploadedFile;
import com.jsofttechnologies.services.file.FileUploadedService;
import com.jsofttechnologies.v2.services.FluidPlatformService;
import com.jsofttechnologies.v2.services.resource.UserResource;
import com.jsofttechnologies.v2.util.WarToken;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.internet.ContentDisposition;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Jerico on 05/08/2015.
 */
/*@Stateless(name = "v2/download")
@Path("services/v2/util")
@UserInfoResource*/
public class DownloadService /*extends FluidPlatformService implements UserResource */ {
/*
    @EJB
    private FileUploadedService fileUploadedService;
    private WarToken warToken;

    @GET
    @Path("/display/{id}")
    public Response display(@PathParam("id") Long uploadedFileID) {
        Response response = Response.ok().build();

        FlowUploadedFile fluidUploadedFile = fileUploadedService.get(uploadedFileID);
        if (fluidUploadedFile != null) {
            try {
                File file = new File(fluidUploadedFile.getPath());
                FileInputStream fileInputStream = new FileInputStream(file);
                response = response.ok(fileInputStream).type(MediaType.APPLICATION_OCTET_STREAM_TYPE).build();
            } catch (Exception e) {
                ejbExceptionHandler.handleException(e, getClass());
                response.ok(messageService.getMessage("DOWNLOAD_ABORTED"));
            }

        } else {
            response.ok(messageService.getMessage("FILE_NOT_FOUND"));
        }

        return response;
    }

    @GET
    @Path("/download/{id}")
    public Response download(@PathParam("id") Long uploadedFileID) {
        Response response = Response.ok().build();

        FlowUploadedFile fluidUploadedFile = fileUploadedService.get(uploadedFileID);
        if (fluidUploadedFile != null) {
            try {
                File file = new File(fluidUploadedFile.getPath());
                FileInputStream fileInputStream = new FileInputStream(file);
                ContentDisposition contentDisposition = new ContentDisposition("attachment;filename=" + fluidUploadedFile.getName());
                response = Response.ok(fileInputStream).type(MediaType.APPLICATION_OCTET_STREAM_TYPE).header("Content-Disposition", contentDisposition).header("Content-Type", fluidUploadedFile.getContentType()).build();
            } catch (Exception e) {
                ejbExceptionHandler.handleException(e, getClass());
                response.ok(messageService.getMessage("DOWNLOAD_ABORTED"));
            }

        } else {
            response.ok(messageService.getMessage("FILE_NOT_FOUND"));
        }

        return response;
    }


    @Override
    public void setWarToken(WarToken warToken) {
        this.warToken = warToken;
    }

    @Override
    public void setAuthenticated(Boolean authenticated) {

    }

    @Override
    public void setToken(String token) {

    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }*/
}
