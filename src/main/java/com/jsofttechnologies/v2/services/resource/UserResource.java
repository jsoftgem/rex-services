package com.jsofttechnologies.v2.services.resource;

import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserDetail;
import com.jsofttechnologies.v2.util.WarToken;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jerico on 02/08/2015.
 */
public interface UserResource {


    public void setWarToken(WarToken warToken);

    public void setAuthenticated(Boolean authenticated);

    public void setToken(String token);

    public HttpServletRequest getRequest();
}
