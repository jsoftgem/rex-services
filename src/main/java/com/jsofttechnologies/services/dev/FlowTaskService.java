package com.jsofttechnologies.services.dev;

import com.jsofttechnologies.ds.EntityManagerDAO;
import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.admin.FlowUserGroupModule;
import com.jsofttechnologies.jpa.admin.FlowUserGroupTask;
import com.jsofttechnologies.jpa.dev.FlowPage;
import com.jsofttechnologies.jpa.dev.FlowTask;
import com.jsofttechnologies.jpa.dev.FlowUserTask;
import com.jsofttechnologies.services.admin.FlowUserGroupModuleQueryService;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jerico on 12/2/2014.
 */

@Stateless
@Path("services/flow_task_service")
public class FlowTaskService extends QueryService<FlowTask> {

    public FlowTaskService() {
        super(FlowTask.class, FlowTask.FIND_ALL);
    }

    @EJB
    private FlowPageQueryService flowPageQueryService;

    @EJB
    private MergeExceptionSummary exceptionSummary;

    @EJB
    private FlowUserTaskQueryService flowUserTaskQueryService;

    @EJB
    private FlowTaskQueryService flowTaskQueryService;

    @EJB
    private FlowUserGroupModuleQueryService flowUserGroupModuleQueryService;


    @EJB
    private EntityManagerDAO<FlowUserTask, Long> entityManagerDAO;

    @SkipCheck("action")
    @GET
    @Path("/getSessionTask")
    @Produces("application/json")
    public FlowTask getSessionTask(@HeaderParam("Authorization") String authorization, @QueryParam("id") Long id, @QueryParam("name") String name, @DefaultValue("25") @QueryParam("size") Integer size, @DefaultValue("false") @QueryParam("active") Boolean active,
                                   @DefaultValue("false") @QueryParam("pinned") Boolean pinned, @DefaultValue("false") @QueryParam("locked") Boolean locked, @DefaultValue("true") @QueryParam("showToolBar") Boolean showToolBar,
                                   @QueryParam("page") String page, @QueryParam("page-path") String pagePath, @QueryParam("flowId") String flowId, @QueryParam("group-default") @DefaultValue("false") Boolean groupDefault) {

        if (id != null) {
            setNamedQuery(FlowTask.FIND_BY_ID);
            putParam("id", id);
        } else if (name != null) {
            setNamedQuery(FlowTask.FIND_BY_NAME);
            putParam("name", name);
        }

        FlowTask flowTask = getSingleResult();

        FlowUserGroupModule flowUserGroupModule = null;
        if (groupDefault) {
            FlowSessionHelper.Promise promise = session.isAuthorized(authorization);

            if (promise.getOk()) {
                if (id == null && name != null) {

                    FlowTask task = flowTaskQueryService.findByName(name);

                    id = task.getId();
                }
                flowUserGroupModule = flowUserGroupModuleQueryService.findByGroupAndTask(promise.getFlowUserGroup().getGroupName(), id);
            }

        }
        if (groupDefault && flowUserGroupModule != null) {
            flowTask.setSize(flowUserGroupModule.getFlowUserGroupTask().getSize());
        } else {
            flowTask.setSize(size);
        }
        flowTask.setActive(active);
        flowTask.setPinned(pinned);
        flowTask.setLocked(locked);
        if (groupDefault && flowUserGroupModule != null) {
            flowTask.setShowToolBar(flowUserGroupModule.getFlowUserGroupTask().getToolBar());
        } else {
            flowTask.setShowToolBar(showToolBar);
        }
        flowTask.setStartDt(new Date());

        if (page != null && !page.isEmpty()) {
            try {
                FlowPage flowPage = new FlowPage();
                flowPage.setName(page);
                if (flowTask.getPages() != null && flowTask.getPages().contains(flowPage)) {
                    List<FlowPage> pages = new ArrayList<>(flowTask.getPages());
                    flowPage = pages.get(pages.indexOf(flowPage));
                    flowTask.setPage(flowPage);

                    if (flowPage != null && pagePath != null) {
                        flowTask.setPageParam(pagePath);
                    }
                }

            } catch (Exception e) {
                exceptionSummary.handleException(e, getClass());
            }
        }


        if (flowId != null) {
            flowTask.setFlowId(flowId);
        }
        return flowTask;
    }

    @SkipCheck("action")
    @GET
    @Path("/getTask")
    public Response getTask(@HeaderParam("Authorization") String authorization, @QueryParam("id") Long id, @QueryParam("name") String name, @DefaultValue("25") @QueryParam("size") Integer size, @DefaultValue("false") @QueryParam("active") Boolean active,
                            @DefaultValue("false") @QueryParam("pinned") Boolean pinned, @DefaultValue("false") @QueryParam("locked") Boolean locked, @DefaultValue("false") @QueryParam("showToolBar") Boolean showToolBar,
                            @QueryParam("page") String page, @QueryParam("page-path") String pagePath, @QueryParam("flowId") String flowId, @QueryParam("group-default") @DefaultValue("false") Boolean groupDefault,
                            @QueryParam("newTask") @DefaultValue("true") Boolean newTask) {

        FlowSessionHelper.Promise authentication = session.isAuthorized(authorization);

        if (authentication.getOk()) {

            if (id != null) {
                setNamedQuery(FlowTask.FIND_BY_ID);
                putParam("id", id);
            } else if (name != null) {
                setNamedQuery(FlowTask.FIND_BY_NAME);
                putParam("name", name);
            } else {
                return null;
            }

            groupDefault = groupDefault ? true : flowId == null;


            FlowTask flowTask = getSingleResult();

            FlowUserTask flowUserTask = null;
            FlowUserGroupModule flowUserGroupModule = flowUserGroupModuleQueryService.findByGroupAndTask(authentication.getFlowUserGroup().getGroupName(), flowTask.getId());
            if (flowUserGroupModule != null) {
                FlowUserGroupTask flowUserGroupTask = flowUserGroupModule.getFlowUserGroupTask();
                if (groupDefault) {

                    if (flowUserGroupTask != null) {
                        if (active == null) {
                            active = flowUserGroupTask.getActive();
                        }

                        if (size == null) {
                            size = flowUserGroupTask.getSize();
                        }

                        if (pinned == null) {
                            pinned = flowUserGroupTask.getPinned();
                        }

                        if (locked == null) {
                            locked = flowUserGroupTask.getLocked();
                        }

                        if (showToolBar == null) {
                            showToolBar = flowUserGroupTask.getToolBar();
                        }

                        if (page == null) {
                            page = flowUserGroupTask.getPage();
                        }
                    }


                    if (flowId == null) {
                        flowUserTask = new FlowUserTask();
                        flowUserTask.setActive(active);
                        flowUserTask.setSize(size);
                        flowUserTask.setPinned(pinned);
                        flowUserTask.setLocked(locked);
                        flowUserTask.setPage(page);
                        flowUserTask.setParam(pagePath);
                        flowUserTask.setFlowTaskId(flowTask.getId());
                        flowUserTask.setFlowUserId(authentication.getFlowUser().getId());
                        try {
                            if (newTask) {
                                flowUserTask = entityManagerDAO.updateObject(flowUserTask);
                                flowId = flowUserTask.getId().toString();
                            }
                        } catch (Exception e) {
                            exceptionSummary.handleException(e, flowUserTask.getClass(), flowUserTask);
                        }
                    }

                } else {
                    flowUserTask = flowUserTaskQueryService.findFlowId(flowId);
                    if (flowUserTask != null) {
                        if (active == null) {
                            active = flowUserTask.getActive();
                        }

                        if (size == null) {
                            size = flowUserTask.getSize();
                        }

                        if (pinned == null) {
                            pinned = flowUserTask.getPinned();
                        }

                        if (locked == null) {
                            locked = flowUserTask.getLocked();
                        }

                        if (page == null) {
                            page = flowUserTask.getPage();
                        }

                        if (pagePath == null) {
                            pagePath = flowUserTask.getParam();
                        }
                    }
                    // If some properties from FlowUserTask are null will get their default.
                    if (active == null) {
                        active = flowUserGroupModule.getFlowUserGroupTask().getActive();
                    }

                    if (size == null) {
                        size = flowUserGroupModule.getFlowUserGroupTask().getSize();
                    }

                    if (pinned == null) {
                        pinned = flowUserGroupModule.getFlowUserGroupTask().getPinned();
                    }

                    if (locked == null) {
                        locked = flowUserGroupModule.getFlowUserGroupTask().getLocked();
                    }

                    if (showToolBar == null) {
                        showToolBar = flowUserGroupModule.getFlowUserGroupTask().getToolBar();
                    }

                    if (page == null) {
                        page = flowUserGroupModule.getFlowUserGroupTask().getPage();
                    }

                }


            }

            if (page != null) {
                FlowPage flowPage = flowPageQueryService.getInstanceByName(page);
                flowTask.setPage(flowPage);
                flowTask.setPageParam(pagePath);
            }

            flowTask.setActive(active);
            flowTask.setSize(size);
            flowTask.setPinned(pinned);
            flowTask.setLocked(locked);
            flowTask.setShowToolBar(showToolBar);
            flowTask.setFlowId(flowId);

            return Response.ok(flowTask, MediaType.APPLICATION_JSON_TYPE).build();
        }

        return authentication.getResponse();
    }

}
