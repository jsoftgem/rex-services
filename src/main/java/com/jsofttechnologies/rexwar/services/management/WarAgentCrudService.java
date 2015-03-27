package com.jsofttechnologies.rexwar.services.management;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.interceptor.Notify;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.jpa.admin.FlowUserProfile;
import com.jsofttechnologies.jpa.util.FlowAlertType;
import com.jsofttechnologies.rexwar.model.management.WarAgent;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.admin.*;
import com.jsofttechnologies.services.util.CrudService;
import com.jsofttechnologies.services.util.MessageService;
import com.jsofttechnologies.util.PasswordHash;
import com.jsofttechnologies.util.ProjectHelper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jerico on 1/11/2015.
 */
@Stateless
@Path("services/war/agent_crud")
@Notify(task = "agent_task", page = "agent_edit", alertType = FlowAlertType.BROADCAST)
public class WarAgentCrudService extends CrudService<WarAgent, Long> {


    @EJB
    private FlowUserGroupQueryService flowUserGroupQueryService;
    @EJB
    private FlowUserProfileQueryService flowUserProfileQueryService;

    @EJB
    private FlowUserQueryService flowUserQueryService;

    @EJB
    private WarAgentQueryService warAgentQueryService;

    @EJB
    private FlowUserGroupCrudService flowUserGroupCrudService;

    @EJB
    private FlowUserCrudService flowUserCrudService;

    @EJB
    private MessageService messageService;

    @EJB
    private MergeExceptionSummary exceptionSummary;

    public WarAgentCrudService() {
        super(WarAgent.class);
    }

    @Override
    protected WarAgent preCreateValidation(WarAgent warAgent) throws Exception {

        FlowUserGroup group = null;
        FlowUserProfile profile = null;
        if (warAgent.getIsManager() == null || (warAgent.getIsManager() != null && !warAgent.getIsManager())) {
            group = flowUserGroupQueryService.findGroupByName(WarConstants.AGENT_GROUP);
            profile = flowUserProfileQueryService.findByProfileName(WarConstants.AGENT_PROFILE);
        } else if (warAgent.getIsManager() != null && warAgent.getIsManager()) {
            group = flowUserGroupQueryService.findGroupByName(WarConstants.AGENT_REGIONAL_MANAGER_GROUP);
            profile = flowUserProfileQueryService.findByProfileName(WarConstants.AGENT_REGIONAL_MANAGER);
        }
        Set<FlowUserProfile> flowUserProfileSet = new HashSet<>();
        warAgent.getUser().setFlowUserProfileSet(flowUserProfileSet);
        warAgent.getUser().setFlowUserGroup(group);
        warAgent.getUser().getFlowUserProfileSet().add(profile);

        FlowUser flowUser = flowUserQueryService.getFlowUserByUsername(warAgent.getUser().getUsername());

        if (flowUser != null) {
            throw new Exception(flowUser.getUsername()
                    + " username already exists!");
        }

        flowUser = flowUserQueryService.getFlowUserByEmail(warAgent.getUser().getEmail());

        if (flowUser != null) {
            throw new Exception(flowUser.getEmail() + " already exists.");
        }

        String password = warAgent.getUser().getPassword();
        // hashPassword
        try {
            password = PasswordHash.createHash(password);
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }
        warAgent.getUser().setPassword(password);


        if (warAgent.getIsManager() != null && warAgent.getIsManager()) {
            WarAgent manager = warAgentQueryService.findManagerByRegion(warAgent.getRegion());
            if (manager != null && manager.getActive()) {
                throwException("WAR_AGENT_EXISTING_MANAGER", manager.getRegion(), manager.getUser().getFlowUserDetail().getFullName());
            }
        }


        return warAgent;
    }

    @Override
    protected WarAgent preUpdateValidation(WarAgent warAgent) throws Exception {

        WarAgent attachedInstance = warAgentQueryService.getById(warAgent.getId());

        FlowUserGroup group = null;
        FlowUserProfile profile = null;
        if (warAgent.getIsManager() == null || (warAgent.getIsManager() != null && !warAgent.getIsManager())) {
            group = flowUserGroupQueryService.findGroupByName(WarConstants.AGENT_GROUP);
            profile = flowUserProfileQueryService.findByProfileName(WarConstants.AGENT_PROFILE);
        } else if (warAgent.getIsManager() != null && warAgent.getIsManager()) {
            group = flowUserGroupQueryService.findGroupByName(WarConstants.AGENT_REGIONAL_MANAGER_GROUP);
            profile = flowUserProfileQueryService.findByProfileName(WarConstants.AGENT_REGIONAL_MANAGER);
        }
        warAgent.getUser().setFlowUserGroup(group);
        warAgent.getUser().getFlowUserProfileSet().add(profile);

        if (!attachedInstance.getUser().getUsername().equals(warAgent.getUser().getUsername())) {
            FlowUser flowUser = flowUserQueryService.getFlowUserByUsername(warAgent.getUser().getUsername());

            if (flowUser != null) {
                throw new Exception(flowUser.getUsername()
                        + " username already exists!");
            }
        }

        if (!attachedInstance.getUser().getEmail().equals(warAgent.getUser().getEmail())) {
            FlowUser flowUser = flowUserQueryService.getFlowUserByEmail(warAgent.getUser().getEmail());

            if (flowUser != null) {
                throw new Exception(flowUser.getEmail() + " already exists.");
            }
        }

        String password = warAgent.getUser().getPassword();
        // hashPassword
        if (password != null && !password.equals(attachedInstance.getUser().getPassword())) {
            try {
                password = PasswordHash.createHash(password);
            } catch (Exception e) {
                exceptionSummary.handleException(e, getClass());
            }
            warAgent.getUser().setPassword(password);
        } else {
            warAgent.getUser().setPassword(attachedInstance.getUser().getPassword());
        }

        return warAgent;
    }

    @Override
    public String createSuccessMessage() {
        return messageService.getMessage(WarConstants.MSG_AGENT_CREATED);
    }

    @Override
    public String updateSuccessMessage() {
        return messageService.getMessage(WarConstants.MSG_AGENT_UPDATED);
    }

    @Override
    protected void postCreateValidation(WarAgent t) {
        t.getUser().getFlowUserGroup().getFlowUsers().add(t.getUser());
        flowUserGroupCrudService.update(t.getUser().getFlowUserGroup(), t.getUser().getFlowUserGroup().getId());
    }

    @DELETE
    @Path("/delete/{id:[0-9][0-9]*}")
    public Response delete(@PathParam("id") Long id) {
        Response response = null;
        try {
            setId(id);
            WarAgent warAgent = getInstance();

            flowUserCrudService.delete(warAgent.getUser().getId());

            if (delete().equalsIgnoreCase("deleted")) {
                response = Response.ok(ProjectHelper.message("Delete successful."),
                        MediaType.APPLICATION_JSON_TYPE).build();
            } else {
                throw new Exception("Delete failed. Check error summary for more info.");
            }

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
            response = Response.serverError()
                    .entity(ProjectHelper.message("Delete failed."))
                    .type(MediaType.APPLICATION_JSON_TYPE).build();
        }

        return response;
    }


}
