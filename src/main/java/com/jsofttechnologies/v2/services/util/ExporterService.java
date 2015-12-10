package com.jsofttechnologies.v2.services.util;

import com.jsofttechnologies.interceptor.UserInfoResource;
import com.jsofttechnologies.v2.services.FluidPlatformService;
import com.jsofttechnologies.v2.services.resource.UserResource;
import com.jsofttechnologies.v2.util.WarToken;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

/**
 * Created by jerico on 10/5/15.
 */
@UserInfoResource
@Path("services/exporter")
public class ExporterService extends FluidPlatformService implements UserResource {


    @Override
    public void setWarToken(WarToken warToken) {

    }

    @Override
    public void setAuthenticated(Boolean authenticated) {

    }

    @Override
    public void setToken(String token) {

    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }
}
