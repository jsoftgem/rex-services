package com.jsofttechnologies.services.file;

import com.jsofttechnologies.jpa.admin.FlowUploadedFile;
import com.jsofttechnologies.v2.services.resource.PageResource;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by rickzx98 on 8/31/15.
 */
@Path("services/v2/file")
@Stateless
public class FileUploadedService extends PageResource<FlowUploadedFile, Long> {

    public FileUploadedService(){
        super(FlowUploadedFile.class);
    }

}
