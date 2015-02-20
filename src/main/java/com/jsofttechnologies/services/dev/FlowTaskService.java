package com.jsofttechnologies.services.dev;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.admin.FlowUserGroupModule;
import com.jsofttechnologies.jpa.dev.FlowPage;
import com.jsofttechnologies.jpa.dev.FlowTask;
import com.jsofttechnologies.services.admin.FlowUserGroupModuleQueryService;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.services.util.QueryService;
import org.jboss.resteasy.spi.HttpRequest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.*;

/**
 * Created by Jerico on 12/2/2014.
 */

@Stateless
@Path("services/flow_task_service")
public class FlowTaskService extends QueryService<FlowTask> {

    public FlowTaskService() {
        super(FlowTask.class, FlowTask.FIND_ALL);
    }

    @Context
    private HttpRequest request;

    @EJB
    private FlowPageQueryService flowPageQueryService;

    @EJB
    private MergeExceptionSummary exceptionSummary;

    @EJB
    private FlowUserTaskCrudService flowUserTaskCrudService;

    @EJB
    private FlowTaskQueryService flowTaskQueryService;

    @EJB
    private FlowUserGroupModuleQueryService flowUserGroupModuleQueryService;

    @EJB
    private FlowSessionHelper flowSessionHelper;

    @SkipCheck("action")
    @GET
    @Path("/getTask")
    @Produces("application/json")
    public FlowTask getTask(@HeaderParam("Authorization") String authorization, @QueryParam("id") Long id, @QueryParam("name") String name, @DefaultValue("25") @QueryParam("size") Integer size, @DefaultValue("false") @QueryParam("active") Boolean active,
                            @DefaultValue("false") @QueryParam("pinned") Boolean pinned, @DefaultValue("false") @QueryParam("locked") Boolean locked, @DefaultValue("true") @QueryParam("showToolBar") Boolean showToolBar,
                            @QueryParam("page") String page, @QueryParam("page-path") String pagePath, @QueryParam("flowId") String flowId, @QueryParam("group-default") @DefaultValue("false") Boolean groupDefault) {

        Map<String, Object> param = new HashMap<>();
        if (id != null) {
            setNamedQuery(FlowTask.FIND_BY_ID);
            param.put("id", id);
        } else if (name != null) {
            setNamedQuery(FlowTask.FIND_BY_NAME);
            param.put("name", name);
        }

        setParam(param);


        FlowTask flowTask = getSingleResult();

        FlowUserGroupModule flowUserGroupModule = null;
        if (groupDefault) {
            FlowSessionHelper.Promise promise = flowSessionHelper.isAuthorized(authorization);

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

       /* if (flowId == null) {
            FlowUserTask flowUserTask = new FlowUserTask();
            flowUserTask.setSize(flowTask.getSize());
            flowUserTask.setPinned(flowTask.getPinned());
            flowUserTask.setLocked(flowTask.getLocked());
            flowUserTask.setActive(flowTask.getActive());
            flowUserTaskCrudService.createUpdate(flowUserTask, null, true);
        }*/
        return flowTask;
    }
}
