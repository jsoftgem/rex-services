package com.jsofttechnologies.services.admin;

import com.jsofttechnologies.jpa.admin.FlowUserAppSetting;
import com.jsofttechnologies.services.util.CrudService;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by Jerico on 1/2/2015.
 */
@Path("services/flow_user_app_setting_crud")
@Stateless
public class FlowUserAppSettingCrudService extends CrudService<FlowUserAppSetting, Long> {

    public FlowUserAppSettingCrudService() {
        super(FlowUserAppSetting.class);
    }

    @Override
    protected FlowUserAppSetting preCreateValidation(FlowUserAppSetting flowUserAppSetting) throws Exception {
        return flowUserAppSetting;
    }

    @Override
    protected FlowUserAppSetting preUpdateValidation(FlowUserAppSetting flowUserAppSetting) throws Exception {
        return flowUserAppSetting;
    }
}
