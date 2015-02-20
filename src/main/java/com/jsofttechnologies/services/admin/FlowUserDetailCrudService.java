package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.interceptor.NotificationInterceptor;
import com.jsofttechnologies.interceptor.Notify;
import com.jsofttechnologies.jpa.admin.FlowUserDetail;
import com.jsofttechnologies.jpa.util.FlowAlertType;
import com.jsofttechnologies.services.util.CrudService;
import com.jsofttechnologies.services.util.MessageService;
import com.jsofttechnologies.util.MsgPropKey;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.Path;

@Path("services/flow_user_detail_crud")
@Stateless
@Interceptors({NotificationInterceptor.class})
@Notify(task = "edit_profile",page = "profile_edit", alertType = FlowAlertType.NOTICE)
public class FlowUserDetailCrudService extends
        CrudService<FlowUserDetail, Long> {
    @EJB
    private MessageService messageService;

    /**
     *
     */

    private static final long serialVersionUID = 7699416291870764757L;

    public FlowUserDetailCrudService() {
        super(FlowUserDetail.class);
    }

    @Override
    public FlowUserDetail preCreateValidation(FlowUserDetail t)
            throws Exception {
        return t;
    }

    @Override
    public FlowUserDetail preUpdateValidation(FlowUserDetail t)
            throws Exception {
        return t;
    }


    @Override
    public String updateSuccessMessage() {
        return messageService.getMessage(MsgPropKey.FLOW_UPDATE_FUDCS);
    }
}
