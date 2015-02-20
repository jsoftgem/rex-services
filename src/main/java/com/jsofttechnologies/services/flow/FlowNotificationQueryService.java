package com.jsofttechnologies.services.flow;

import com.jsofttechnologies.jpa.flow.FlowNotification;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.Date;
import java.util.List;

/**
 * Created by Jerico on 12/22/2014.
 */
@Path("services/flow_notification_query")
@Stateless
public class FlowNotificationQueryService extends QueryService<FlowNotification> {

    public FlowNotificationQueryService() {
        super(FlowNotification.class, FlowNotification.FIND_ALL);
    }

    public List<FlowNotification> getUserTop(int limit, long userId) {
        setNamedQuery(FlowNotification.FIND_USER);
        putParam("userId", userId);
        setMax(limit);
        return doGetResultList();
    }

    public List<FlowNotification> getUserAll(long userId) {
        setNamedQuery(FlowNotification.FIND_USER);
        putParam("userId", userId);
        return doGetResultList();
    }
    public List<FlowNotification> getGroupTop(int limit, String group) {
        setNamedQuery(FlowNotification.FIND_GROUP);
        putParam("group", group);
        setMax(limit);
        return doGetResultList();
    }

    public List<FlowNotification> getGroupAll(String group) {
        setNamedQuery(FlowNotification.FIND_GROUP);
        putParam("group", group);
        return doGetResultList();
    }

    public List<FlowNotification> getUserAlert(long userId) {
        setNamedQuery(FlowNotification.FIND_UNNOTICED_USER);
        putParam("userId", userId);

        List<FlowNotification> flowNotificationList1 = doGetResultList();

        for (int i = 0; i < flowNotificationList1.size(); i++) {
            FlowNotification flowNotification = flowNotificationList1.get(i);
            long startDate = flowNotification.getStartDt().getTime();
            if (startDate > new Date().getTime()) {
                flowNotificationList1.remove(i);
            }
        }

        return flowNotificationList1;
    }

    public List<FlowNotification> getGroupAlert(String group) {
        setNamedQuery(FlowNotification.FIND_UNNOTICED_GROUP);
        putParam("group", group);

        List<FlowNotification> flowNotificationList1 = doGetResultList();

        for (int i = 0; i < flowNotificationList1.size(); i++) {
            FlowNotification flowNotification = flowNotificationList1.get(i);
            long startDate = flowNotification.getStartDt().getTime();
            if (startDate > new Date().getTime()) {
                flowNotificationList1.remove(i);
            }
        }

        return flowNotificationList1;
    }


}
