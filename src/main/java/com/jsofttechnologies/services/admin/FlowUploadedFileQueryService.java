package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.jpa.admin.FlowUploadedFile;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 12/15/2014.
 */
@Stateless
@Path("services/flow_uploaded_file_query")
public class FlowUploadedFileQueryService extends QueryService<FlowUploadedFile> {

    public FlowUploadedFileQueryService() {
        super(FlowUploadedFile.class, FlowUploadedFile.FIND_ALL);
    }





}
