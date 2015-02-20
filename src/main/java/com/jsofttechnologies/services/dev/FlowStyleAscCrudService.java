package com.jsofttechnologies.services.dev;

import com.jsofttechnologies.jpa.dev.FlowStyleAsc;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 11/11/2014.
 */
@Path("services/flow_style_asc_crud")
@Stateless
public class FlowStyleAscCrudService extends CrudService<FlowStyleAsc, Long> {

    public FlowStyleAscCrudService() {
        super(FlowStyleAsc.class);
    }

    @Override
    protected FlowStyleAsc preCreateValidation(FlowStyleAsc flowStyleAsc) throws Exception {
        return flowStyleAsc;
    }

    @Override
    protected FlowStyleAsc preUpdateValidation(FlowStyleAsc flowStyleAsc) throws Exception {
        return flowStyleAsc;
    }
}
