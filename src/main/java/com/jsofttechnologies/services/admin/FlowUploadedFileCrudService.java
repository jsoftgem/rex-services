package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.jpa.admin.FlowUploadedFile;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

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
}
