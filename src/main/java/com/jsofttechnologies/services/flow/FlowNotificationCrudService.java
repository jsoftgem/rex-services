package com.jsofttechnologies.services.flow;

import com.jsofttechnologies.jpa.flow.FlowNotification;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 12/22/2014.
 */
@Path("flow_notification_crud")
@Stateless
public class FlowNotificationCrudService extends CrudService<FlowNotification, Long> {

    public FlowNotificationCrudService() {
        super(FlowNotification.class);
    }

    @Override
    protected FlowNotification preCreateValidation(FlowNotification flowNotification) throws Exception {
        return flowNotification;
    }

    @Override
    protected FlowNotification preUpdateValidation(FlowNotification flowNotification) throws Exception {
        return flowNotification;
    }
}
