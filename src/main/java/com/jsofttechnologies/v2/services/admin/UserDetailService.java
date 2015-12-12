package com.jsofttechnologies.v2.services.admin;

import com.jsofttechnologies.jpa.admin.FlowUserDetail;
import com.jsofttechnologies.v2.services.resource.PageResource;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * Created by rickzx98 on 8/30/15.
 */
@Stateless
@Path("/services/v2/user/detail")
public class UserDetailService extends PageResource<FlowUserDetail,Long> {

    public UserDetailService(){
        super(FlowUserDetail.class);
    }




}
