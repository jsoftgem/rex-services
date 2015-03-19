package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.jpa.admin.FlowUploadedFile;
import com.jsofttechnologies.services.util.CrudService;
import com.jsofttechnologies.util.FileUtil;
import com.jsofttechnologies.util.ProjectConstants;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.io.File;

/**
 * Created by Jerico on 12/14/2014.
 */
@Stateless
@Path("services/flow_uploaded_file_crud")
public class FlowUploadedFileCrudService extends CrudService<FlowUploadedFile, Long> {

    public FlowUploadedFileCrudService() {
        super(FlowUploadedFile.class);
    }

    @Override
    protected FlowUploadedFile preCreateValidation(FlowUploadedFile flowUploadedFile) throws Exception {
        return flowUploadedFile;
    }

    @Override
    protected FlowUploadedFile preUpdateValidation(FlowUploadedFile flowUploadedFile) throws Exception {
        return flowUploadedFile;
    }

    @Override
    protected FlowUploadedFile preDeleteValidation(FlowUploadedFile flowUploadedFile) {
        File home = FileUtil
                .createFolder(ProjectConstants.FILE_SERVER_HOME);

        File fileService = FileUtil
                .createFolder(home, ProjectConstants.FILE_SERVER_PATH);

        File root = FileUtil.createFolders(fileService, flowUploadedFile.getFolder());

        File folder = FileUtil.createFolder(root, flowUploadedFile.getType());

        File file = new File(folder, flowUploadedFile.getName());

        if (file.exists()) {
            file.delete();
        }

        return super.preDeleteValidation(flowUploadedFile);
    }
}
