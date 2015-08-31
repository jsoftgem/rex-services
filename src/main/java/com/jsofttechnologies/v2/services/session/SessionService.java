package com.jsofttechnologies.v2.services.session;

import com.jsofttechnologies.jpa.admin.FlowSession;
import com.jsofttechnologies.v2.services.resource.PageResource;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.Date;

/**
 * Created by rickzx98 on 8/31/15.
 */
@Stateless(name = "v2/session")
@Path("services/v2/session")
public class SessionService extends PageResource<FlowSession, Long> {


    public SessionService() {
        super(FlowSession.class);
    }

    public void createSession(Long userId, String token, String remoteAddress) {
        FlowSession flowSession = new FlowSession();
        flowSession.setUserSessionKey(token);
        flowSession.setActive(Boolean.TRUE);
        flowSession.setUserHost(remoteAddress);
        flowSession.setUserId(userId);
        flowSession.setLastAccessDate(new Date());
        flowSession.setSecured(Boolean.TRUE/*TODO: create a service to determine if its an authorized host/user*/);
        save(flowSession);
    }



}
