package com.jsofttechnologies.services.dev;

import com.jsofttechnologies.ejb.FlowUserManager;
import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.dev.FlowUserTask;
import com.jsofttechnologies.services.util.CrudService;
import com.jsofttechnologies.util.ProjectConstants;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.util.Base64;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Jerico on 11/2/2014.
 */
@Path("services/flow_user_task_crud")
@Stateless
public class FlowUserTaskCrudService extends CrudService<FlowUserTask, Long> {

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
    public FlowUserTask createUpdate(FlowUserTask flowUserTask, @QueryParam("field") String field, @QueryParam("newTask") Boolean newTask) {
        try {
            List<String> authorization = request.getHttpHeaders().getRequestHeader(ProjectConstants.HEADER_AUTHORIZATION);
            //If no authorization information present; block access
            if (authorization == null || authorization.isEmpty()) {
                return null;
            }

            //Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(ProjectConstants.AUTHENTICATION_SCHEME + " ", "");

            //Decode username and password
            String usernameAndPassword = null;

            try {
                usernameAndPassword = new String(Base64.decode(encodedUserPassword));
            } catch (IOException e) {
                exceptionSummary.handleException(e, getClass());
            }

            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");

            final String username = tokenizer.nextToken();

            Long userId = flowUserManager.getUserId(username);

            flowUserTask.setFlowUserId(userId);
            if (newTask == null || (newTask != null && newTask == Boolean.FALSE)) {
                List<FlowUserTask> flowUserTaskList = entityManager.createNamedQuery(FlowUserTask.FIND_BY_ID).setParameter("id", Long.valueOf(flowUserTask.getFlowId())).getResultList();
                FlowUserTask persistedFlowUserTask = null;
                if (flowUserTaskList != null && !flowUserTaskList.isEmpty()) {
                    persistedFlowUserTask = flowUserTaskList.get(0);
                    persistedFlowUserTask.setFlowId(flowUserTask.getFlowId());
                }
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
                            persistedFlowUserTask.setActive(Boolean.FALSE);
                            persistedFlowUserTask.setClosed(true);
                            entityManager.merge(persistedFlowUserTask);
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


        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), flowUserTask);
        }
        return flowUserTask;
    }
}
