package com.jsofttechnologies.services.dev;


import com.jsofttechnologies.jpa.dev.FlowGroup;
import com.jsofttechnologies.jpa.dev.FlowModule;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.List;

@Path("services/flow_task_group_crud")
@Stateless
public class FlowTaskGroupCrudService extends
        CrudService<FlowGroup, Long> {

    public FlowTaskGroupCrudService() {
        super(FlowGroup.class);
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected FlowGroup preCreateValidation(FlowGroup t)
            throws Exception {
        List<FlowModule> flowModuleList = t.getFlowModules();
        for (FlowModule flowModule : flowModuleList) {
            flowModule.setFlowGroupName(t.getName());
        }
        return t;
    }

    @Override
    protected FlowGroup preUpdateValidation(FlowGroup t)
            throws Exception {
        List<FlowModule> flowModuleList = t.getFlowModules();
        for (FlowModule flowModule : flowModuleList) {
            flowModule.setFlowGroupName(t.getName());
        }
        return t;
    }

}
