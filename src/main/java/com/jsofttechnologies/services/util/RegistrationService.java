package com.jsofttechnologies.services.util;


import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.admin.FlowUserDetail;
import com.jsofttechnologies.model.RegistrationModel;
import com.jsofttechnologies.util.EmailUtil;
import com.jsofttechnologies.util.PasswordHash;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("services/registration_service")
@Stateless
public class RegistrationService extends FlowService {

    @EJB
    private EmailUtil emailUtil;

    /**
     *
     */
    private static final long serialVersionUID = -3852316719531349837L;

    @PersistenceContext(unitName = ProjectConstants.MAIN_PU)
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @PUT
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(RegistrationModel registrationModel) {

        Response response = null;

        try {

            List<FlowUser> userList = entityManager
                    .createNamedQuery(FlowUser.FIND_BY_USERNAME)
                    .setParameter("username", registrationModel.getUsername())
                    .getResultList();

            if (userList != null && !userList.isEmpty()) {
                return Response
                        .serverError()
                        .entity("{\"msg\":\"" + registrationModel.getUsername()
                                + " already exists.\"}")
                        .type(MediaType.APPLICATION_JSON_TYPE).build();
            }

            userList = entityManager.createNamedQuery(FlowUser.FIND_BY_EMAIL)
                    .setParameter("email", registrationModel.getEmail())
                    .getResultList();

            if (userList != null && !userList.isEmpty()) {
                return Response
                        .serverError()
                        .entity("{\"msg\":\"" + registrationModel.getEmail()
                                + " already exists.\"}")
                        .type(MediaType.APPLICATION_JSON_TYPE).build();
            }

            FlowUser flowUser = new FlowUser();

            flowUser.setUsername(registrationModel.getUsername());
            flowUser.setEmail(registrationModel.getEmail());

            String hashPassword = PasswordHash.createHash(registrationModel
                    .getPassword());
            flowUser.setPassword(hashPassword);


            FlowUserDetail flowUserDetail = new FlowUserDetail();
            if (registrationModel.getImageUri() != null) {
            /*    flowUserDetail.setImageUri(registrationModel.getImageUri());*/
            }
            flowUserDetail.setFullName(registrationModel.getFullName());
            flowUser.setFlowUserDetail(flowUserDetail);

            entityManager.persist(flowUser);
            entityManager.flush();
            entityManager.clear();

            emailUtil.send(ProjectConstants.REG_SUBJECT, "Hi "
                            + flowUserDetail.getFullName()
                            + "!\nWelcome to MergeIT Web Service.",
                    flowUser.getEmail());

            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("msg", flowUser.getUsername() + " has been created.");
            jsonMap.put("id", flowUser.getId());
            response = Response.ok(ProjectHelper.json(jsonMap))
                    .type(MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            response = ProjectHelper.error(e);
        }
        return response;
    }
}
