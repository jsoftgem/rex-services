package com.jsofttechnologies.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserDetail;
import com.jsofttechnologies.v2.services.resource.UserResource;
import com.jsofttechnologies.v2.util.WarToken;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jerico on 02/08/2015.
 */
@Interceptor
@UserInfoResource
public class UserResourceProcessor {

    @AroundInvoke
    public Object setValues(InvocationContext ic) throws Exception {

        Object target = ic.getTarget();


        if (target instanceof UserResource) {

            HttpServletRequest request = ((UserResource) target).getRequest();

            if (request != null) {
                if (request.getHeader("Authorization") != null) {
                    Object userInfo = request.getAttribute("info");
                    String token = request.getHeader("Authorization").replace("bearer ", "");
                    if (userInfo != null && token != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        WarToken info = mapper.readValue(userInfo.toString(), WarToken.class);
                        ((UserResource) target).setWarToken(info);
                        ((UserResource) target).setToken(token);
                        ((UserResource) target).setAuthenticated(Boolean.TRUE);
                    }
                } else {
                    ((UserResource) target).setWarToken(null);
                    ((UserResource) target).setToken(null);
                    ((UserResource) target).setAuthenticated(Boolean.FALSE);
                }
            }
        }
        return ic.proceed();
    }

}
