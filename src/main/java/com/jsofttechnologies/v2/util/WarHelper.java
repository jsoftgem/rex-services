package com.jsofttechnologies.v2.util;

import com.jsofttechnologies.jpa.admin.FlowUserGroupModule;
import com.jsofttechnologies.jpa.admin.FlowUserGroupTask;
import com.jsofttechnologies.jpa.dev.FlowUserTask;

import java.util.List;

/**
 * Created by rickzx98 on 8/31/15.
 */
public class WarHelper {

    public static FlowUserGroupTask findGroupTask(FlowUserTask flowUserTask, List<FlowUserGroupModule> flowUserGroupModules) {
        for (FlowUserGroupModule flowUserGroupModule : flowUserGroupModules) {
            if (flowUserGroupModule.getFlowUserGroupTask().getFlowUserTaskId() == flowUserTask.getFlowTaskId()) {
                return flowUserGroupModule.getFlowUserGroupTask();
            }
        }
        return null;
    }
}
