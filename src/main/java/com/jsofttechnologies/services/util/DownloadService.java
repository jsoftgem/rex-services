package com.jsofttechnologies.services.util;

import com.jsofttechnologies.jpa.admin.FlowUploadedFile;
import com.jsofttechnologies.services.admin.FlowUploadedFileQueryService;
import com.jsofttechnologies.util.FileUtil;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("services/download_service")
@Stateless
public class DownloadService extends FlowService {

    /**
     *
     */
    private static final long serialVersionUID = 8968791684590764101L;


    @EJB
    private FlowUploadedFileQueryService flowUploadedFileQueryService;

    @GET
    @Path("user_profile_image/{id}")
    public Response downloadUserProfile(@PathParam("id") Long userId) {

        Response response = null;

        File root = FileUtil.createFolder(ProjectConstants.FILE_COMMON_USER);

        File userFolder = FileUtil.createFolder(root, "_" + userId);
        File userProfileImageFolder = FileUtil.createFolder(userFolder,
                ProjectConstants.FOLDER_PROFILE_IMAGE);
        File[] images = userProfileImageFolder.listFiles();
        int length = images.length;

        if (length > 0) {
            response = Response
                    .ok(images[0], MediaType.APPLICATION_OCTET_STREAM_TYPE)
                    .header("file-name", images[0].getName()).build();
        } else {
            response = Response.ok().entity("assets/controls/dup.png")
                    .type(MediaType.TEXT_PLAIN_TYPE).build();
        }

        return response;
    }

    @GET
    @Path("image/{base_folder}/{file_name}")
    public Response downloadImage(@PathParam("base_folder") String baseFolder,
                                  @PathParam("file_name") String fileName) {

        Response response = null;

        File fileService = null;
        if (ProjectConstants.ENV == ProjectConstants.ENV_PROD) {
            fileService = FileUtil.createFolder(FileUtil
                    .createFolder(System.getProperty(ProjectConstants.FILE_SERVER_VAR)), ProjectConstants.FILE_SERVER_PATH);
        } else {
            fileService = FileUtil
                    .createFolder(ProjectConstants.FILE_SERVER_PATH);
        }

        File root = FileUtil.createFolder(fileService, baseFolder);

        File folder = FileUtil.createFolder(root, "img");

        File file = new File(folder, fileName);

        if (file.exists()) {
            response = Response
                    .ok(file, MediaType.APPLICATION_OCTET_STREAM_TYPE)
                    .header("file-name", file.getName()).build();
        } else {
            response = Response.ok().entity("assets/controls/dup.png")
                    .type(MediaType.TEXT_PLAIN_TYPE).build();
        }

        return response;
    }

    @GET
    @Path("video/{base_folder}/{file_name}")
    public Response downloadVideo(@PathParam("base_folder") String baseFolder,
                                  @PathParam("file_name") String fileName) {

        Response response = null;

        File fileService = null;
        if (ProjectConstants.ENV == ProjectConstants.ENV_PROD) {
            fileService = FileUtil.createFolder(FileUtil
                    .createFolder(System.getProperty(ProjectConstants.FILE_SERVER_VAR)), ProjectConstants.FILE_SERVER_PATH);
        } else {
            fileService = FileUtil
                    .createFolder(ProjectConstants.FILE_SERVER_PATH);
        }

        File root = FileUtil.createFolder(fileService, baseFolder);

        File folder = FileUtil.createFolder(root, "video");

        File file = new File(folder, fileName);

        if (file.exists()) {
            response = Response
                    .ok(file, MediaType.APPLICATION_OCTET_STREAM_TYPE)
                    .header("file-name", file.getName()).build();
        } else {
            response = Response.ok().entity("assets/controls/dup.png")
                    .type(MediaType.TEXT_PLAIN_TYPE).build();
        }

        return response;
    }

    @GET
    @PermitAll
    @Path("getContent/{id:[0-9][0-9]*}")
    public Response getFileContent(@PathParam("id") Long id) {
        FlowUploadedFile flowUploadedFile = flowUploadedFileQueryService.getById(id);
        Response response;

        if (id == null) {
            return Response.ok("", MediaType.APPLICATION_JSON_TYPE).build();
        }

        File fileService = null;
        if (ProjectConstants.ENV == ProjectConstants.ENV_PROD) {
            fileService = FileUtil.createFolder(FileUtil
                    .createFolder(System.getProperty(ProjectConstants.FILE_SERVER_VAR)), ProjectConstants.FILE_SERVER_PATH);
        } else {
            fileService = FileUtil
                    .createFolder(ProjectConstants.FILE_SERVER_PATH);
        }

        File root = FileUtil.createFolders(fileService, flowUploadedFile.getFolder());

        File folder = FileUtil.createFolder(root, flowUploadedFile.getType());

        File file = new File(folder, flowUploadedFile.getName());

        if (file.exists()) {
            response = Response
                    .ok(file)
                    .header("Content-Disposition", "attachment;filename=\"" + flowUploadedFile.getDescription() + "\"")
                    .header("Content-Type", flowUploadedFile.getContentType()).build();
        } else {
            response = Response.ok().entity(ProjectConstants.FLOW_DOWNLOAD_DEFAULT_IMG)
                    .type(MediaType.TEXT_PLAIN_TYPE).build();
        }

        return response;
    }

    @GET
    @PermitAll
    @Path("getInfo/{id:[0-9][0-9]*}")
    public Response getFileInfo(@PathParam("id") Long id) {
        FlowUploadedFile flowUploadedFile = flowUploadedFileQueryService.getById(id);
        Response response;


        File fileService = null;

        if (ProjectConstants.ENV == ProjectConstants.ENV_PROD) {
            fileService = FileUtil.createFolder(FileUtil
                    .createFolder(System.getProperty(ProjectConstants.FILE_SERVER_VAR)), ProjectConstants.FILE_SERVER_PATH);
        } else {
            fileService = FileUtil
                    .createFolder(ProjectConstants.FILE_SERVER_PATH);
        }
        File root = FileUtil.createFolders(fileService, flowUploadedFile.getFolder());

        File folder = FileUtil.createFolder(root, flowUploadedFile.getType());

        File file = new File(folder, flowUploadedFile.getName());


        if (file.exists()) {

            ProjectHelper projectHelper = new ProjectHelper();
            projectHelper.createJson()
                    .addField("fileName", file.getName())
                    .addField("filePath", file.getAbsolutePath());
            response = Response
                    .ok(projectHelper.buildJsonString(), MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            response = Response.ok().entity(ProjectConstants.FLOW_DOWNLOAD_DEFAULT_IMG)
                    .type(MediaType.TEXT_PLAIN_TYPE).build();
        }

        return response;
    }


}
