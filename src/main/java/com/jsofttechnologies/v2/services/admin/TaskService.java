package com.jsofttechnologies.v2.services.admin;

import com.jsofttechnologies.interceptor.UserInfoResource;
import com.jsofttechnologies.jpa.admin.FlowUserGroupModule;
import com.jsofttechnologies.jpa.admin.FlowUserGroupTask;
import com.jsofttechnologies.jpa.dev.FlowTask;
import com.jsofttechnologies.jpa.dev.FlowUserTask;
import com.jsofttechnologies.services.admin.FlowUserGroupModuleQueryService;
import com.jsofttechnologies.services.dev.FlowUserTaskQueryService;
import com.jsofttechnologies.v2.services.resource.PageResource;
import com.jsofttechnologies.v2.util.WarHelper;
import com.jsofttechnologies.v2.util.WarToken;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rickzx98 on 8/31/15.
 */
@Path("services/v2/task")
@Stateless
@UserInfoResource
public class TaskService extends PageResource<FlowTask, Long> {
    @EJB
    private UserService userService;
    @EJB
    private ModuleService moduleService;
    @EJB
    private FlowUserTaskQueryService flowUserTaskQueryService;
    @EJB
    private FlowUserGroupModuleQueryService flowUserGroupModuleQueryService;

    public TaskService() {
        super(FlowTask.class);
    }

    private WarToken warToken;
    private Boolean authenticated;

    @Override
    public void setWarToken(WarToken warToken) {
        this.warToken = warToken;
    }

    @Override
    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    @GET
    @Path("user")
    public List<String> getSessionTasks() {
        if (authenticated) {
            List<String> tasks = new ArrayList<>();

            List<FlowUserTask> flowUserTaskList = flowUserTaskQueryService.findAllByUserId(warToken.getUserId());
            String endPoint = "services/flow_task_service/getTask";

            if (flowUserTaskList != null && !flowUserTaskList.isEmpty()) {
                List<FlowUserGroupModule> flowUserGroupModuleList = flowUserGroupModuleQueryService.findByGroupName(warToken.getGroup());

                for (FlowUserTask flowUserTask : flowUserTaskList) {
                    FlowUserGroupTask flowUserGroupTask = null;
                    if (isCached(flowUserTask.getFlowTaskId(), FlowUserGroupTask.class)) {
                        flowUserGroupTask = serviceMap(flowUserTask.getFlowTaskId(), flowUserGroupTask, FlowUserGroupTask.class, false);
                    } else {
                        flowUserGroupTask = WarHelper.findGroupTask(flowUserTask, flowUserGroupModuleList);
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
                return tasks;
            }

        }
        return null;
    }

}
