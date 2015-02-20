package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.admin.FlowUserAppSetting;
import com.jsofttechnologies.services.util.QueryService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by Jerico on 1/2/2015.
 */
@Path("services/flow_user_app_setting_query")
@Stateless
public class FlowUserAppSettingQueryService extends QueryService<FlowUserAppSetting> {
    @EJB
    private MergeExceptionSummary exceptionSummary;

    public FlowUserAppSettingQueryService() {
        super(FlowUserAppSetting.class, FlowUserAppSetting.FIND_ALL);
    }


    public FlowUserAppSetting getByUserId(Long userId) {
        try {
            setNamedQuery(FlowUserAppSetting.FIND_BY_USER);
            putParam("userId", userId);
            List<FlowUserAppSetting> flowUserAppSettings = doGetResultList();
            if (flowUserAppSettings != null && !flowUserAppSettings.isEmpty()) {
                return flowUserAppSettings.get(0);
            }
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }
        return null;
    }

}
