package com.jsofttechnologies.services.dev;

import com.jsofttechnologies.jpa.dev.FlowStyle;
import com.jsofttechnologies.services.util.CrudService;

import javax.ws.rs.Path;

@Path("services/flow_style_crud")
public class FlowStyleCrudService extends CrudService<FlowStyle, Long> {

    public FlowStyleCrudService() {
        super(FlowStyle.class);
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected FlowStyle preCreateValidation(FlowStyle t) throws Exception {
        
        return t;
    }

    @Override
    protected FlowStyle preUpdateValidation(FlowStyle t) throws Exception {
        
        return t;
    }

}
