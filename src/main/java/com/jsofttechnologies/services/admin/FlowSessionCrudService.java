package com.jsofttechnologies.services.admin;


import com.jsofttechnologies.jpa.admin.FlowSession;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Path("services/merge_session_crud")
@Stateless
public class FlowSessionCrudService extends CrudService<FlowSession, Long> {

    /**
     *
     */
    private static final long serialVersionUID = 4282933823018590397L;

    public FlowSessionCrudService() {
        super(FlowSession.class);
    }

    @Override
    protected FlowSession preCreateValidation(FlowSession t) throws Exception {
        return t;
    }

    @Override
    protected FlowSession preUpdateValidation(FlowSession t) throws Exception {
        return t;
    }

}
