package com.jsofttechnologies.services.session;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.admin.FlowUserAppSetting;
import com.jsofttechnologies.services.admin.FlowUserAppSettingCrudService;
import com.jsofttechnologies.services.admin.FlowUserAppSettingQueryService;
import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.services.util.FlowSessionHelper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jerico on 1/2/2015.
 */
@Path("session/flow_user_app_setting_session")
@Stateless
public class FlowUserAppSettingSession extends FlowService {

    @EJB
    private FlowSessionHelper flowSessionHelper;

    @EJB
    private FlowUserAppSettingQueryService flowUserAppSettingQueryService;

    @EJB
    private FlowUserAppSettingCrudService flowUserAppSettingCrudService;

    @SkipCheck("action")
    @Path("setting")
    @GET
    public Response getFlowUserAppSetting(@HeaderParam("Authorization") String authorization) {
        Response response = null;

        FlowSessionHelper.Promise promise = flowSessionHelper.isAuthorized(authorization);


        if (promise.getOk()) {

            FlowUserAppSetting flowUserAppSetting = flowUserAppSettingQueryService.getByUserId(promise.getFlowUser().getId());

            response = Response.ok(flowUserAppSetting, MediaType.APPLICATION_JSON_TYPE).build();

        } else {
            response = promise.getResponse();
        }


        return response;
    }

    @SkipCheck("action")
    @Path("update")
    @PUT
    public Response updateSetting(@HeaderParam("Authorization") String authorization,
                                  @QueryParam("menu") String menu,
                                  @QueryParam("theme") String theme,
                                  @QueryParam("bgColor") String bgColor,
                                  @QueryParam("style") String style,
                                  @QueryParam("hideMenu") Boolean hideMenu) {
        Response response = null;

        FlowSessionHelper.Promise promise = flowSessionHelper.isAuthorized(authorization);


        if (promise.getOk()) {
            FlowUserAppSetting flowUserAppSetting = flowUserAppSettingQueryService.getByUserId(promise.getFlowUser().getId());

            if (flowUserAppSetting == null) {
                flowUserAppSetting = new FlowUserAppSetting();
                flowUserAppSetting.setUserId(promise.getFlowUser().getId());
            }
            if (menu != null) {
                flowUserAppSetting.setMenu(menu);
            }
            if (theme != null) {
                flowUserAppSetting.setTheme(theme);
            }
            if (bgColor != null) {
                flowUserAppSetting.setBgColor(bgColor);
            }
            if (style != null) {
                flowUserAppSetting.setStyle(style);
            }
            if(hideMenu != null){
                //flowUserAppSetting.setHideMenu(hideMenu);
            }
            response = flowUserAppSettingCrudService.update(flowUserAppSetting, flowUserAppSetting.getId());

        } else {
            response = promise.getResponse();
        }


        return response;
    }


}
