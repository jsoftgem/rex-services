package com.jsofttechnologies.services.migration;

import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.services.util.LoginService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.URL;

/**
 * Created by Jerico on 3/20/2015.
 */
@Path("services/migration_service")
@Stateless
public class MigrationService extends FlowService {

    @EJB
    private LoginService loginService;

    @SkipCheck("authorization")
    @Path("csv/{folder}")
    public void migrate(@PathParam("folder") String folder, @QueryParam("username") String username, @QueryParam("password") String password) {
        Response response = loginService.basicAuth(username, password, request);


        if (response.getStatus() == 200) {
            //  JSONObject authResponse = new JSONObject(response.getEntity());

            String file = "/migration/" + folder + "/*.csv";

            URL url = getClass().getResource(file);

            System.out.println(url);

        }

    }


}
