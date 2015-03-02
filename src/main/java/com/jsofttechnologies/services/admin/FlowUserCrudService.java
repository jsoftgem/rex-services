package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.model.PaperBag;
import com.jsofttechnologies.services.util.CrudService;
import com.jsofttechnologies.util.PasswordHash;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("services/flow_user_crud")
@Stateless
public class FlowUserCrudService extends CrudService<FlowUser, Long> {

    /**
     *
     */
    private static final long serialVersionUID = 6992136637241092232L;

    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

    @EJB
    private MergeExceptionSummary exceptionSummary;
    @EJB
    private FlowUserGroupCrudService flowUserGroupCrudService;
    @EJB
    private FlowUserGroupQueryService flowUserGroupQueryService;
    @EJB
    private FlowUserQueryService flowUserQueryService;


    public FlowUserCrudService() {
        super(FlowUser.class);
    }


    @SuppressWarnings("unchecked")
    @Override
    public FlowUser preCreateValidation(FlowUser flowUser) throws Exception {

        List<FlowUser> userList = entityManager
                .createNamedQuery(FlowUser.FIND_BY_USERNAME)
                .setParameter("username", flowUser.getUsername())
                .getResultList();

        if (userList != null && !userList.isEmpty()) {
            throw new Exception(flowUser.getUsername()
                    + " username already exists!");
        }
        userList = entityManager.createNamedQuery(FlowUser.FIND_BY_EMAIL)
                .setParameter("email", flowUser.getEmail()).getResultList();

        if (userList != null && !userList.isEmpty()) {
            throw new Exception(flowUser.getEmail() + " already exists.");
        }
        String password = flowUser.getPassword();
        // hashPassword
        try {
            password = PasswordHash.createHash(password.trim());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        flowUser.setPassword(password);

        return flowUser;
    }

    @Override
    protected void postCreateValidation(FlowUser flowUser) {

        if (flowUser.getFlowUserGroup() != null) {
            flowUser.getFlowUserGroup().getFlowUsers().add(flowUser);
            flowUserGroupCrudService.update(flowUser.getFlowUserGroup(), flowUser.getFlowUserGroup().getId());
        }
    }

    @Override
    protected void postUpdateValidation(FlowUser flowUser) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public FlowUser preUpdateValidation(FlowUser flowUser) throws Exception {

        FlowUser attachedInstance = flowUserQueryService.getById(flowUser.getId());

        FlowUserGroup oldGroup = attachedInstance.getFlowUserGroup();

        if (!oldGroup.equals(flowUser.getFlowUserGroup())) {
            if (oldGroup.getFlowUsers() != null) {
                if (oldGroup.getFlowUsers().contains(flowUser)) {
                    oldGroup.getFlowUsers().remove(flowUser);
                    flowUserGroupCrudService.update(oldGroup, oldGroup.getId());
                }
            }
            if (flowUser.getFlowUserGroup() != null) {
                if (!flowUser.getFlowUserGroup().getFlowUsers().contains(flowUser)) {
                    flowUser.getFlowUserGroup().getFlowUsers().add(flowUser);
                    flowUserGroupCrudService.update(flowUser.getFlowUserGroup(), flowUser.getFlowUserGroup().getId());
                }

            }
            attachedInstance.setFlowUserGroup(flowUser.getFlowUserGroup());
        }

        attachedInstance.setFlowUserProfileSet(flowUser.getFlowUserProfileSet());

        boolean sameUsername = attachedInstance.getUsername().equals(
                flowUser.getUsername());

        if (!sameUsername) {
            List<FlowUser> userList = entityManager
                    .createNamedQuery(FlowUser.FIND_BY_USERNAME)
                    .setParameter("username", flowUser.getUsername())
                    .getResultList();

            if (userList != null && !userList.isEmpty()) {
                throw new Exception(flowUser.getUsername()
                        + " username already exists!");
            }
            attachedInstance.setUsername(flowUser.getUsername());
        }

        boolean sameEmail = attachedInstance.getEmail().equals(flowUser.getEmail());

        if (!sameEmail) {
            List<FlowUser> userList = entityManager.createNamedQuery(FlowUser.FIND_BY_EMAIL)
                    .setParameter("email", flowUser.getEmail())
                    .getResultList();

            if (userList != null && !userList.isEmpty()) {
                throw new Exception(flowUser.getEmail() + " already exists.");
            }

            attachedInstance.setEmail(flowUser.getEmail());
        }


        String password = flowUser.getPassword();
        // hashPassword
        if (password != null) {
            try {
                password = PasswordHash.createHash(password.trim());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            attachedInstance.setPassword(password);
        }

        if (attachedInstance.getFlowUserDetail() != null) {
            if (!attachedInstance.getFlowUserDetail().equals(flowUser.getFlowUserDetail())) {
                attachedInstance.setFlowUserDetail(flowUser.getFlowUserDetail());
            }
        }

        return attachedInstance;
    }


    @Path("/update/{id:[0-9][0-9]*}")
    @PUT
    @Consumes("application/json")
    public Response update(PaperBag<FlowUser> flowUserBag, @PathParam("id") Long id) {
        Response response = null;

        setInstance(flowUserBag.getFlowInstance());

        FlowUser currentInstance = getInstance();

        try {

            setId(id);
            currentInstance.setFlowUserGroup(flowUserBag.getFlowUserGroup());

            String save = save();

            if (save.equalsIgnoreCase("not saved"))
                throw new Exception("Update failed. Check error summary for more info.");

            Map<String, Object> jsonMap = new HashMap<>();
            response = Response.ok(ProjectHelper.message("Update successful!"))
                    .type(MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), flowUserBag);
            response = ProjectHelper.error(e);
        }
        return response;
    }


    @Path("/create")
    @PUT
    @Consumes("application/json")
    public Response create(PaperBag<FlowUser> flowUserBag) {
        Response response = null;
        setInstance(flowUserBag.getFlowInstance());
        FlowUser current = getInstance();
        current.setFlowUserGroup(flowUserBag.getFlowUserGroup());

        try {
            String save = save();
            if (save.equalsIgnoreCase("not saved"))
                throw new Exception("Save failed. Check error summary for more info.");
            Long id = (Long) getInstance().getId();
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("id", id);
            jsonMap.put("msg", "Create successful!");
            response = Response.ok(ProjectHelper.json(jsonMap))
                    .type(MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), flowUserBag);
            response = ProjectHelper.error(e);
        }
        return response;
    }


    @DELETE
    @Path("/delete/{id:[0-9][0-9]*}")
    public Response delete(@PathParam("id") Long id) {
        Response response = null;
        try {
            setId(id);
            FlowUser flowUser = getInstance();

            FlowUserGroup flowUserGroup = flowUserGroupQueryService.findGroupByUserId(flowUser.getId());

            flowUserGroup.getFlowUsers().remove(flowUser);

            flowUserGroupCrudService.update(flowUserGroup, flowUserGroup.getId());

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
