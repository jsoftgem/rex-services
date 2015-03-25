package com.jsofttechnologies.services.dev;

import com.jsofttechnologies.ejb.FlowUserManager;
import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.dev.FlowUserTask;
import com.jsofttechnologies.services.util.CrudService;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.util.ProjectConstants;
import org.jboss.resteasy.spi.HttpRequest;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

/**
 * Created by Jerico on 11/2/2014.
 */
@Path("services/flow_user_task_crud")
@Stateless
public class FlowUserTaskCrudService extends CrudService<FlowUserTask, Long> {


    @EJB
    private FlowUserTaskQueryService flowUserTaskQueryService;

    public FlowUserTaskCrudService() {
        super(FlowUserTask.class);
    }

    @Context
    private HttpRequest request;

    @EJB
    private MergeExceptionSummary exceptionSummary;

    @EJB
    private FlowUserManager flowUserManager;

    @PersistenceContext(name = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

    @Override
    protected FlowUserTask preCreateValidation(FlowUserTask flowUserTask) throws Exception {
        return flowUserTask;
    }

    @Override
    protected FlowUserTask preUpdateValidation(FlowUserTask flowUserTask) throws Exception {
        return flowUserTask;
    }

    @PermitAll
    @POST
    @Path("save_task_state")
    @Consumes("application/json")
    @Produces("application/json")
    public FlowUserTask createUpdate(@HeaderParam("Authorization") String authorization, FlowUserTask flowUserTask, @QueryParam("field") String field, @QueryParam("newTask") Boolean newTask) {
        try {

            FlowSessionHelper.Promise promise = session.isAuthorized(authorization);
            if (promise.getOk()) {
                flowUserTask.setFlowUserId(promise.getFlowUser().getId());
                if (newTask == null || (newTask != null && newTask == Boolean.FALSE)) {

                    FlowUserTask persistedFlowUserTask = flowUserTask.getFlowId() != null ? flowUserTaskQueryService.getById(Long.valueOf(flowUserTask.getFlowId().trim())) : null;
                    if (field != null) {
                        switch (field) {
                            case "active":
                            case "ACTIVE":
                                persistedFlowUserTask.setActive(flowUserTask.getActive());
                                break;
                            case "size":
                            case "SIZE":
                                persistedFlowUserTask.setSize(flowUserTask.getSize());
                                break;
                            case "pin":
                            case "PIN":
                                persistedFlowUserTask.setPinned(flowUserTask.getPinned());
                                persistedFlowUserTask.setPage(flowUserTask.getPage());
                                persistedFlowUserTask.setParam(flowUserTask.getParam());
                                break;
                            case "close":
                            case "CLOSED":
                                entityManager.remove(persistedFlowUserTask);
                                if (entityManager.isJoinedToTransaction()) {
                                    entityManager.flush();
                                    entityManager.getTransaction().commit();
                                }
                                break;
                        }
                    }
                    if (field != null && !field.equalsIgnoreCase("close") && persistedFlowUserTask != null) {
                        entityManager.merge(persistedFlowUserTask);
                        return persistedFlowUserTask;
                    }

                } else {
                    flowUserTask.setActive(Boolean.TRUE);
                    entityManager.persist(flowUserTask);
                    entityManager.flush();
                    entityManager.refresh(flowUserTask);
                }
            }

        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), flowUserTask);
        }
        return flowUserTask;
    }
}
