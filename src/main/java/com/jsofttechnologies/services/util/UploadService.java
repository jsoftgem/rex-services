package com.jsofttechnologies.services.util;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.admin.FlowUploadedFile;
import com.jsofttechnologies.security.SessionHelper;
import com.jsofttechnologies.services.admin.FlowUploadedFileCrudService;
import com.jsofttechnologies.services.admin.FlowUploadedFileQueryService;
import com.jsofttechnologies.util.FileUtil;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Path("services/upload_service")
@Stateless
public class UploadService extends FlowService {

    @EJB
    private SessionHelper sessionHelper;

    /**
     *
     */
    private static final long serialVersionUID = -7950097311934871274L;
    @Context
    private ServletContext context;

    @Context
    private HttpServletRequest request;


    @EJB
    private MergeExceptionSummary exceptionSummary;

    @EJB
    private FlowUploadedFileCrudService flowUploadedFileCrudService;
    @EJB
    private FlowUploadedFileQueryService flowUploadedFileQueryService;

    @POST
    @Path("upload_profile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(MultipartFormDataInput input) {
        Response response = null;
        String imgUri = null;
        String fileName = null;
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("uploadedFile");
        for (InputPart inputPart : inputParts) {

            try {

                String sessionId = request
                        .getHeader(ProjectConstants.HEADER_SESSION_TOKEN);

                Long userId = sessionHelper.getCurrentUserID(sessionId);

                MultivaluedMap<String, String> header = inputPart.getHeaders();
                System.out.println("header: " + header);
                fileName = getFileName(header);

                // convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class,
                        null);

                byte[] bytes = IOUtils.toByteArray(inputStream);

                // constructs upload file path
                File dir = FileUtil
                        .createFolder(ProjectConstants.FILE_COMMON_USER);

                File userFolder = FileUtil.createFolder(dir, "_" + userId);

                File userProfileImageFolder = FileUtil.createFolder(userFolder,
                        ProjectConstants.FOLDER_PROFILE_IMAGE);

                System.out.println("filename: " + fileName);
                fileName = userId
                        + fileName.substring(fileName.lastIndexOf('.'));
                fileName = fileName.replaceAll("\\s", "").trim().toLowerCase();

                File uploadedFile = new File(userProfileImageFolder, fileName);
                boolean written = writeFile(bytes, uploadedFile);

                if (written) {
                    imgUri = ProjectConstants.SERVICE_DOWNLOAD_USER_PROFILE_IMAGE
                            + "/" + userId;
                }

                response = Response.ok(imgUri).type(MediaType.TEXT_PLAIN_TYPE)
                        .build();
            } catch (IOException e) {
                response = ProjectHelper.error(e);
            }

        }
        return response;
    }

    @POST
    @Path("upload_image/{base_folder}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadTo(@PathParam("base_folder") String baseFolder, @QueryParam("name") String name,
                             MultipartFormDataInput input) {
        Response response = null;
        String imgUri = null;
        String fileName = null;
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get(name);
        for (InputPart inputPart : inputParts) {

            try {

                MultivaluedMap<String, String> header = inputPart.getHeaders();
                System.out.println("header: " + header);
                fileName = getFileName(header);

                // convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class,
                        null);

                byte[] bytes = IOUtils.toByteArray(inputStream);

                File fileService = null;

                if (ProjectConstants.ENV == ProjectConstants.ENV_PROD) {
                    fileService = FileUtil.createFolder(FileUtil
                            .createFolder(System.getProperty(ProjectConstants.FILE_SERVER_VAR)), ProjectConstants.FILE_SERVER_PATH);
                } else {
                    fileService = FileUtil
                            .createFolder(ProjectConstants.FILE_SERVER_PATH);
                }

                File dir = FileUtil.createFolder(fileService, baseFolder);

                File folder = FileUtil.createFolder(dir, "img");

                fileName = fileName.replaceAll("\\s", "").trim().toLowerCase();

                File uploadedFile = new File(folder, fileName);
                boolean written = writeFile(bytes, uploadedFile);

                if (written) {
                    imgUri = ProjectConstants.SERVICE_DOWNLOAD_IMAGE + "/"
                            + baseFolder + "/" + fileName;
                }

                response = Response.ok(imgUri).type(MediaType.TEXT_PLAIN_TYPE)
                        .build();
            } catch (IOException e) {
                response = ProjectHelper.error(e);
            }

        }
        return response;
    }

    @POST
    @SkipCheck("action")
    @Path("upload_file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@QueryParam("file-name") String fileName, @QueryParam("folder") String baseFolder, @DefaultValue("attachment") @QueryParam("type") String type, MultipartFormDataInput formDataInput) {
        Response response = Response.ok().build();
        try {

            Map<String, List<InputPart>> uploadForm = formDataInput.getFormDataMap();
            List<InputPart> inputParts = uploadForm.get("file");
            InputPart inputPart = inputParts.get(0);

            InputStream inputStream = inputPart.getBody(InputStream.class, null);
            byte[] bytes = IOUtils.toByteArray(inputStream);

            String uploadedField = request.getHeader("flowUploadFileId");
            FlowUploadedFile flowUploadedFile = new FlowUploadedFile();

            if (uploadedField != null && !uploadedField.isEmpty() && !uploadedField.equalsIgnoreCase("null")) {
                flowUploadedFile = flowUploadedFileQueryService.getById(Long.valueOf(uploadedField));
                flowUploadedFile.setSize(bytes.length);
                flowUploadedFileCrudService.update(flowUploadedFile, flowUploadedFile.getId());
            } else {
                flowUploadedFile.setSize(bytes.length);
                flowUploadedFile.setDescription(fileName);
                flowUploadedFile.setType(type);
                flowUploadedFile.setContentType(inputPart.getMediaType().toString());
                flowUploadedFile.setFolder(baseFolder);
                flowUploadedFileCrudService.create(flowUploadedFile);
            }


            File fileService = null;

            if (ProjectConstants.ENV == ProjectConstants.ENV_PROD) {
                fileService = FileUtil.createFolder(FileUtil
                        .createFolder(System.getProperty(ProjectConstants.FILE_SERVER_VAR)), ProjectConstants.FILE_SERVER_PATH);
            } else {
                fileService = FileUtil
                        .createFolder(ProjectConstants.FILE_SERVER_PATH);
            }

            File dir = FileUtil.createFolders(fileService, baseFolder);

            File folder = FileUtil.createFolder(dir, flowUploadedFile.getType());

            File uploadedFile = new File(folder, flowUploadedFile.getName());

            if (!uploadedFile.exists()) {
                uploadedFile.createNewFile();
            }

            FileOutputStream fop = new FileOutputStream(uploadedFile);
            fop.write(bytes);
            fop.flush();
            fop.close();

            response = Response.ok().entity(flowUploadedFile).type(MediaType.APPLICATION_JSON_TYPE).build();


        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }


        return response;
    }


    protected String getBoundary(String contentType) {
        return contentType.split("boundary=")[1];
    }


}
