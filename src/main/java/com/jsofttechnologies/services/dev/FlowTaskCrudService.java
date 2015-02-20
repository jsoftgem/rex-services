package com.jsofttechnologies.services.dev;


import com.jsofttechnologies.jpa.dev.FlowTask;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Path("services/flow_task_crud")
@Stateless
public class FlowTaskCrudService extends CrudService<FlowTask, Long> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FlowTaskCrudService() {
        super(FlowTask.class);
    }


    @Override
    protected FlowTask preCreateValidation(FlowTask t) throws Exception {
        return t;
    }

    @Override
    protected FlowTask preUpdateValidation(FlowTask t) throws Exception {
        return t;
    }

}
