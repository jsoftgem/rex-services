package com.jsofttechnologies.v2.services.admin;

/**
 * Created by Jerico on 26/08/2015.
 */

import com.jsofttechnologies.interceptor.UserInfoResource;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.util.PasswordHash;
import com.jsofttechnologies.v2.services.resource.PageResource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by Jerico on 7/25/2015.
 */
@Stateless
@Path("/services/v2/user")
@UserInfoResource
public class UserService extends PageResource<FlowUser, Long> {

    private FlowUser info;

    public UserService() {
        super(FlowUser.class);
    }


    @Context
    private HttpServletRequest request;

    public void validate(FlowUser fluidUser) throws Exception {

        if (fluidUser.getId() == null) {
            if (fluidUser.getUsername() != null) {
                Response existingUser = get(FlowUser.FIND_BY_USERNAME,
                        new String[]{"username"}, new String[]{"string"},
                        new String[]{fluidUser.getUsername()});

                if (existingUser != null && existingUser.getEntity() instanceof FlowUser) {
                    throw new Exception(messageService.getMessage("USERNAME_EXISTS"));
                }

            } else {
                throw new Exception(messageService.getMessage("USERNAME_REQUIRED"));
            }


            if (fluidUser.getEmail() != null) {
                Response existingEmail = get(FlowUser.FIND_BY_EMAIL,
                        new String[]{"email"}, new String[]{"string"},
                        new String[]{fluidUser.getUsername()});

                if (existingEmail != null && existingEmail.getEntity() instanceof FlowUser) {
                    throw new Exception(messageService.getMessage("EMAIL_EXISTS"));
                }
            } else {
                throw new Exception(messageService.getMessage("EMAIL_REQUIRED"));
            }


            if (fluidUser.getPassword() != null) {
                String passwordHash = PasswordHash.createHash(fluidUser.getPassword());
                fluidUser.setPassword(passwordHash);
            } else {
                throw new Exception(messageService.getMessage("PASSWORD_REQUIRED"));

            }
        } else {

            FlowUser oldUser = get(fluidUser.getId());


            if (fluidUser.getUsername() != null && !fluidUser.getUsername().equals(oldUser.getUsername())) {
                Response existingUser = get(FlowUser.FIND_BY_USERNAME,
                        new String[]{"username"}, new String[]{"string"},
                        new String[]{fluidUser.getUsername()});

                if (existingUser != null && existingUser.getEntity() instanceof FlowUser) {
                    throw new Exception(messageService.getMessage("USERNAME_EXISTS"));
                }
            }


            if (fluidUser.getEmail() != null && !fluidUser.getEmail().equals(oldUser.getEmail())) {

                Response existingEmail = get(FlowUser.FIND_BY_EMAIL,
                        new String[]{"email"}, new String[]{"string"},
                        new String[]{fluidUser.getUsername()});

                if (existingEmail != null && existingEmail.getEntity() instanceof FlowUser) {
                    throw new Exception(messageService.getMessage("EMAIL_EXISTS"));
                }
            }


        }

    }


    @Override
    public void onSave(FlowUser fluidUser) throws Exception {
        validate(fluidUser);
    }

    public void onView() throws Exception {
        System.out.println(info);

    }

    public void setInfo(FlowUser info) {
        this.info = info;
    }
}
