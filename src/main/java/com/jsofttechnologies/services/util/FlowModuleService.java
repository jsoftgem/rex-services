package com.jsofttechnologies.services.util;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.admin.FlowUserGroupModule;
import com.jsofttechnologies.jpa.admin.FlowUserGroupTask;
import com.jsofttechnologies.jpa.dev.FlowGroup;
import com.jsofttechnologies.jpa.dev.FlowModule;
import com.jsofttechnologies.jpa.dev.FlowUserTask;
import com.jsofttechnologies.services.admin.FlowUserGroupModuleQueryService;
import com.jsofttechnologies.services.dev.FlowUserTaskQueryService;
import com.jsofttechnologies.util.ProjectConstants;
import org.jboss.resteasy.util.Base64;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Jerico on 12/4/2014.
 */
@Path("services/flow_module_service")
@Stateless
public class FlowModuleService extends FlowService {


    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

    @EJB
    private FlowUserTaskQueryService flowUserTaskQueryService;

    @EJB
    private FlowUserGroupModuleQueryService flowUserGroupModuleQueryService;

    @SkipCheck("action")
    @GET
    @Path("session_modules")
    @Produces("application/json")
    public List<FlowGroup> getSessionModules(@HeaderParam(ProjectConstants.HEADER_AUTHORIZATION) String authorization) {


        FlowSessionHelper.Promise promise = session.isAuthorized(authorization);

        if (promise.getOk()) {

            String groupName = promise.getFlowUserGroup().getGroupName();

            List<FlowModule> flowModuleList = entityManager.createNamedQuery(FlowModule.FIND_BY_FLOW_USER_GROUP_NAME, FlowModule.class).setParameter("groupName", groupName).getResultList();

            Set<String> moduleGroupSet = flowModuleList.stream().map(FlowModule::getFlowGroupName).collect(Collectors.toSet());

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaQuery<FlowGroup> criteriaQuery = cb.createQuery(FlowGroup.class);

            Root<FlowGroup> groupRoot = criteriaQuery.from(FlowGroup.class);

            List<Predicate> predicates = moduleGroupSet.stream().map(gName -> cb.equal(groupRoot.get("name"), gName)).collect(Collectors.toList());

            criteriaQuery.select(groupRoot).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));


            TypedQuery<FlowGroup> tTypedQuery = entityManager.createQuery(criteriaQuery);

            List<FlowGroup> groupList = tTypedQuery.getResultList();


            for (FlowGroup flowGroup : groupList) {
                flowGroup.getFlowModules().clear();
                flowModuleList.stream().filter(module -> flowGroup.getName().equals(module.getFlowGroupName())).forEach(module -> {
                    flowGroup.getFlowModules().add(module);
                });
                Collections.sort(flowGroup.getFlowModules(), new Comparator<FlowModule>() {
                    @Override
                    public int compare(FlowModule o1, FlowModule o2) {
                        return o1.getModuleTitle().compareTo(o2.getModuleTitle());
                    }
                });
            }

            return groupList;
        }
        return null;
    }


    @SkipCheck("action")
    @GET
    @Path("session_tasks")
    public Response getSessionTasks(@HeaderParam(ProjectConstants.HEADER_AUTHORIZATION) String authorization) {

        FlowSessionHelper.Promise promise = session.isAuthorized(authorization);

        if (promise.getOk()) {
            List<FlowUserGroupModule> flowUserGroupModules = entityManager.createNamedQuery(FlowUserGroupModule.FIND_BY_USER_GROUP, FlowUserGroupModule.class).setParameter("groupName", promise.getFlowUserGroup().getGroupName()).getResultList();

            Long userId = promise.getFlowUser().getId();

            for (FlowUserGroupModule flowUserGroupModule : flowUserGroupModules) {

                List<FlowUserTask> flowUserTaskList = entityManager.createNamedQuery(FlowUserTask.FIND_BY_TASK_AND_USER).setParameter("taskId", flowUserGroupModule.getFlowUserGroupTask().getFlowUserTaskId()).setParameter("userId", userId).getResultList();
                if (flowUserTaskList != null && !flowUserTaskList.isEmpty()) {
                    FlowUserTask flowUserTask = flowUserTaskList.get(0);
                    if (flowUserTask.getActive() != null)
                        flowUserGroupModule.getFlowUserGroupTask().setActive(flowUserTask.getActive());
                    if (flowUserTask.getLocked() != null)
                        flowUserGroupModule.getFlowUserGroupTask().setLocked(flowUserTask.getLocked());
                    if (flowUserTask.getPinned() != null)
                        flowUserGroupModule.getFlowUserGroupTask().setPinned(flowUserTask.getPinned());
                    if (flowUserTask.getSize() != null)
                        flowUserGroupModule.getFlowUserGroupTask().setSize(flowUserTask.getSize());
                    if (flowUserTask.getPage() != null)
                        flowUserGroupModule.getFlowUserGroupTask().setPage(flowUserTask.getPage());
                    if (flowUserTask.getParam() != null)
                        flowUserGroupModule.getFlowUserGroupTask().setParam(flowUserTask.getParam());
                }
            }

            return Response.ok(flowUserGroupModules, MediaType.APPLICATION_JSON_TYPE).build();
        }


        return promise.getResponse();

    }

    @POST
    @SkipCheck("action")
    @Path("user_tasks")
    public Response getUserSessionTask(@HeaderParam(ProjectConstants.HEADER_AUTHORIZATION) String authorization) {
        Response response = null;

        FlowSessionHelper.Promise promise = session.isAuthorized(authorization);

        if (promise.getOk()) {
            List<String> tasks = new ArrayList<>();

            List<FlowUserTask> flowUserTaskList = flowUserTaskQueryService.findAllByUserId(promise.getFlowUser().getId());
            String endPoint = "services/flow_task_service/getTask";

            if (flowUserTaskList != null && !flowUserTaskList.isEmpty()) {
                List<FlowUserGroupModule> flowUserGroupModuleList = flowUserGroupModuleQueryService.findByGroupName(promise.getFlowUserGroup().getGroupName());

                for (FlowUserTask flowUserTask : flowUserTaskList) {
                    FlowUserGroupTask flowUserGroupTask = null;
                    if (isCached(flowUserTask.getFlowTaskId(), FlowUserGroupTask.class)) {
                        flowUserGroupTask = serviceMap(flowUserTask.getFlowTaskId(), flowUserGroupTask, FlowUserGroupTask.class, false);
                    } else {
                        flowUserGroupTask = findGroupTask(flowUserTask, flowUserGroupModuleList);
                        if (flowUserGroupTask != null) {
                            serviceMap(flowUserTask.getFlowTaskId(), flowUserGroupTask, FlowUserGroupTask.class, false);
                        }
                    }

                    boolean active = false;
                    int size = 25;
                    boolean locked = false;
                    boolean pinned = false;
                    boolean closed = false;
                    boolean showToolbar = false;
                    String page = null;
                    String pagePath = null;

                    if (flowUserGroupTask != null) {
                        if (flowUserGroupTask.getActive() != null) {
                            active = flowUserGroupTask.getActive();
                        }
                        if (flowUserGroupTask.getSize() != null) {
                            size = flowUserGroupTask.getSize();
                        }

                        if (flowUserGroupTask.getClosed() != null) {
                            closed = flowUserGroupTask.getClosed();
                        }

                        if (flowUserGroupTask.getPinned() != null) {
                            pinned = flowUserGroupTask.getPinned();
                        }

                        if (flowUserGroupTask.getLocked() != null) {
                            locked = flowUserGroupTask.getLocked();
                        }

                        if (flowUserGroupTask.getToolBar() != null) {
                            showToolbar = flowUserGroupTask.getToolBar();
                        }
                    }

                    if (flowUserTask != null) {
                        if (flowUserTask.getActive() != null) {
                            active = flowUserTask.getActive();
                        }

                        if (flowUserTask.getSize() != null) {
                            size = flowUserTask.getSize();
                        }

                        if (flowUserTask.getClosed() != null) {
                            closed = flowUserTask.getClosed();
                        }

                        if (flowUserTask.getPinned() != null) {
                            pinned = flowUserTask.getPinned();
                        }

                        if (flowUserTask.getPage() != null) {
                            page = flowUserTask.getPage();
                        }

                        if (flowUserTask.getParam() != null) {
                            pagePath = flowUserTask.getParam();
                        }

                        if (flowUserTask.getLocked() != null) {
                            locked = flowUserTask.getLocked();
                        }

                    }


                    StringBuilder taskService = new StringBuilder(endPoint);
                    taskService.append("?id=").append(flowUserTask.getFlowTaskId());
                    taskService.append("&flowId=").append(flowUserTask.getId());


                    if (showToolbar) {
                        taskService.append("&showToolBar=true");
                    } else {
                        taskService.append("&showToolBar=false");
                    }

                    if (active) {
                        taskService.append("&active=true");
                    } else {
                        taskService.append("&active=false");
                    }

                    if (locked) {
                        taskService.append("&locked=true");
                    } else {
                        taskService.append("&locked=false");
                    }


                    taskService.append("&size=" + size);

                    if (pinned) {
                        taskService.append("&pinned=").append(pinned);
                        taskService.append("&page=").append(page);
                        taskService.append("&page-path=").append(pagePath);
                    }


                    tasks.add(taskService.toString());
                }
            }
          return Response.ok(tasks,MediaType.APPLICATION_JSON_TYPE).build();
        }


        response = promise.getResponse();
        return response;
    }

/*

    @PermitAll
    @GET
    @Path("user_tasks")
    public Response getUserSessionTask() {
        Map<Long, FlowUserGroupTask> groupTaskMap = new HashMap<>();

        List<String> tasks = new ArrayList<>();
        List<String> authorization = request.getHttpHeaders().getRequestHeader(ProjectConstants.HEADER_AUTHORIZATION);
        //If no authorization information present; block access
        if (authorization == null || authorization.isEmpty()) {
            return Response.serverError().status(ProjectConstants.STATUS_NOT_AUTHENTICATED).build();
        }
        //Get encoded username and password
        final String encodedUserPassword = authorization.get(0).replaceFirst(ProjectConstants.AUTHENTICATION_SCHEME + " ", "");

        //Decode username and password
        String usernameAndPassword;

        try {
            usernameAndPassword = new String(Base64.decode(encodedUserPassword));
        } catch (IOException e) {
            exceptionSummary.handleException(e, getClass());
            return Response.serverError().status(ProjectConstants.STATUS_NOT_AUTHENTICATED).build();
        }

        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");

        final String username = tokenizer.nextToken();

        String groupName = flowUserManager.getGroupName(username);


        Long userId = flowUserManager.getUserId(username);


        List<FlowUserTask> flowUserTaskList = entityManager.createNamedQuery(FlowUserTask.FIND_BY_USER).setParameter("userId", userId).getResultList();

        String endPoint = "services/flow_task_service/getTask";


        if (flowUserTaskList != null && !flowUserTaskList.isEmpty()) {
            List<FlowUserGroupModule> flowUserGroupModules = entityManager.createNamedQuery(FlowUserGroupModule.FIND_BY_USER_GROUP, FlowUserGroupModule.class).setParameter("groupName", groupName).getResultList();

            for (FlowUserTask flowUserTask : flowUserTaskList) {

                if (!groupTaskMap.containsKey(flowUserTask.getFlowTaskId())) {
                    FlowUserGroupTask flowUserGroupTask = findGroupTask(flowUserTask, flowUserGroupModules);
                    if (flowUserGroupTask != null) {
                        groupTaskMap.put(flowUserTask.getFlowTaskId(), findGroupTask(flowUserTask, flowUserGroupModules));
                    }
                }

                StringBuilder taskService = new StringBuilder(endPoint);
                taskService.append("?id=").append(flowUserTask.getFlowTaskId());
                taskService.append("&flowId=").append(flowUserTask.getId());

                if (groupTaskMap.containsKey(flowUserTask.getFlowTaskId())) {
                    taskService.append("&showToolBar=").append(groupTaskMap.get(flowUserTask.getFlowTaskId()).getToolBar() != null ? groupTaskMap.get(flowUserTask.getFlowTaskId()).getToolBar() : Boolean.FALSE);
                } else {
                    taskService.append("&showToolBar=").append(Boolean.FALSE);
                }

                if (flowUserTask.getActive() != null) {
                    taskService.append("&active=").append(flowUserTask.getActive());
                } else {
                    if (groupTaskMap.containsKey(flowUserTask.getFlowTaskId())) {
                        taskService.append("&active=").append(groupTaskMap.get(flowUserTask.getFlowTaskId()).getActive());
                    }
                }

                if (flowUserTask.getLocked() != null) {
                    taskService.append("&locked=").append(flowUserTask.getLocked());
                } else {
                    if (groupTaskMap.containsKey(flowUserTask.getFlowTaskId())) {
                        taskService.append("&locked=").append(groupTaskMap.get(flowUserTask.getFlowTaskId()).getLocked());
                    }
                }

                if (flowUserTask.getSize() != null) {
                    taskService.append("&size=").append(flowUserTask.getSize());
                } else {
                    if (groupTaskMap.containsKey(flowUserTask.getFlowTaskId())) {
                        taskService.append("&size=").append(groupTaskMap.get(flowUserTask.getFlowTaskId()).getSize());
                    }
                }

                if (flowUserTask.getPinned() != null && flowUserTask.getPinned()) {
                    taskService.append("&pinned=").append(flowUserTask.getPinned());
                    taskService.append("&page=").append(flowUserTask.getPage());
                    taskService.append("&page-path=").append(flowUserTask.getParam());
                    tasks.add(taskService.toString());
                } else {

                    if (groupTaskMap.containsKey(flowUserTask.getFlowTaskId())) {
                        if (groupTaskMap.get(flowUserTask.getFlowTaskId()).getClosed() != null && groupTaskMap.get(flowUserTask.getFlowTaskId()).getClosed()) {
                            tasks.add(taskService.toString());
                        }
                    } else if (flowUserTask.getClosed() != null && !flowUserTask.getClosed()) {
                        tasks.add(taskService.toString());
                    }
                }
            }

        } else {

            List<FlowUserGroupModule> flowUserGroupModules = entityManager.createNamedQuery(FlowUserGroupModule.FIND_BY_USER_GROUP, FlowUserGroupModule.class).setParameter("groupName", groupName).getResultList();

            for (FlowUserGroupModule flowUserGroupModule : flowUserGroupModules) {
                StringBuilder taskService = new StringBuilder(endPoint);
                taskService.append("?id=").append(flowUserGroupModule.getFlowUserGroupTask().getFlowUserTaskId());

                if (flowUserGroupModule.getFlowUserGroupTask().getActive() != null) {
                    taskService.append("&active=").append(flowUserGroupModule.getFlowUserGroupTask().getActive());
                } else {
                    taskService.append("&active=").append(false);
                }

                if (flowUserGroupModule.getFlowUserGroupTask().getLocked() != null) {
                    taskService.append("&locked=").append(flowUserGroupModule.getFlowUserGroupTask().getLocked());
                }

                if (flowUserGroupModule.getFlowUserGroupTask().getSize() != null) {
                    taskService.append("&size=").append(flowUserGroupModule.getFlowUserGroupTask().getSize());
                }

                if (flowUserGroupModule.getFlowUserGroupTask().getPinned() != null && flowUserGroupModule.getFlowUserGroupTask().getPinned()) {
                    taskService.append("&pinned=").append(flowUserGroupModule.getFlowUserGroupTask().getPinned());
                    taskService.append("&page=").append(flowUserGroupModule.getFlowUserGroupTask().getPage());
                    taskService.append("&page-path=").append(flowUserGroupModule.getFlowUserGroupTask().getParam());
                    tasks.add(taskService.toString());
                } else if (flowUserGroupModule.getFlowUserGroupTask().getClosed() != null && !flowUserGroupModule.getFlowUserGroupTask().getClosed()) {
                    tasks.add(taskService.toString());
                }

            }

        }

        return Response.ok(tasks).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
*/


    private FlowUserGroupTask findGroupTask(FlowUserTask flowUserTask, List<FlowUserGroupModule> flowUserGroupModules) {
        for (FlowUserGroupModule flowUserGroupModule : flowUserGroupModules) {
            if (flowUserGroupModule.getFlowUserGroupTask().getFlowUserTaskId() == flowUserTask.getFlowTaskId()) {
                return flowUserGroupModule.getFlowUserGroupTask();
            }
        }
        return null;
    }
}
