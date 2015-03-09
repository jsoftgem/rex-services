package com.jsofttechnologies.rexwar.services.activity;

import com.jsofttechnologies.rexwar.model.activity.WarPlannerAttachment;
import com.jsofttechnologies.services.admin.FlowUploadedFileCrudService;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 3/8/2015.
 */
@Path("services/war/planner_attachment_crud")
@Stateless
public class WarPlannerAttachmentCrudService extends CrudService<WarPlannerAttachment, Long> {

    @EJB
    private FlowUploadedFileCrudService flowUploadedFileCrudService;

    public WarPlannerAttachmentCrudService() {
        super(WarPlannerAttachment.class);
    }

    @Override
    protected WarPlannerAttachment preCreateValidation(WarPlannerAttachment warPlannerAttachment) throws Exception {
        return warPlannerAttachment;
    }

    @Override
    protected WarPlannerAttachment preUpdateValidation(WarPlannerAttachment warPlannerAttachment) throws Exception {
        return warPlannerAttachment;
    }

    protected WarPlannerAttachment preDeleteValidation(WarPlannerAttachment warPlannerAttachment) {
        flowUploadedFileCrudService.delete(warPlannerAttachment.getUploadedFileId());
        return warPlannerAttachment;
    }
}
