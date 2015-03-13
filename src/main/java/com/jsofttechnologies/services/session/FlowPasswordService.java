package com.jsofttechnologies.services.session;

import com.jsofttechnologies.ds.EntityManagerDAO;
import com.jsofttechnologies.ejb.FlowUserManager;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.services.admin.FlowUserQueryService;
import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.util.PasswordHash;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Jerico on 3/13/2015.
 */
@Path("session/password_service")
@Stateless
public class FlowPasswordService extends FlowService {

    @EJB
    private FlowUserManager flowUserManager;
    @EJB
    private EntityManagerDAO<FlowUser, Long> flowUserEntityManagerDAO;
    @EJB
    private FlowUserQueryService flowUserQueryService;


    @POST
    @Path("validate/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validatedPassword(String password, @PathParam("username") String username) {
        FlowUser user = flowUserManager.getUser(flowUserManager.getUserId(username));
        boolean valid = false;
        try {
            valid = PasswordHash.validatePassword(password, user.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return Response.ok(valid).build();
    }

    @POST
    @Path("change_password/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changePassword(String password, @PathParam("username") String username) {

        Response response = null;


        try {
            FlowUser user = flowUserQueryService.getFlowUserByUsername(username);
            user.setPassword(PasswordHash.createHash(password));
            flowUserEntityManagerDAO.updateObject(user);
            flowUserManager.refreshUserMap();
            response = Response.ok().build();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), username);
        }

        return response;
    }


}
