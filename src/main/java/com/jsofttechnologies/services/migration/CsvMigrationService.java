package com.jsofttechnologies.services.migration;

import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.services.util.LoginService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Created by Jerico on 3/20/2015.
 */
@Path("services/migration_service")
@Stateless
public class CsvMigrationService extends FlowService {

    @EJB
    private LoginService loginService;


    @Path("migrate/{folder}")
    public void migrate(@PathParam("folder") String folder, @QueryParam("username") String username, @QueryParam("password") String password) {
        Response response = loginService.basicAuth(username, password, request);


        if (response.getStatus() == Response.Status.OK.ordinal()) {
            //  JSONObject authResponse = new JSONObject(response.getEntity());


        }

    }


}
