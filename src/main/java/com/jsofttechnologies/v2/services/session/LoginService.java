package com.jsofttechnologies.v2.services.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jsofttechnologies.interceptor.SkipCheck;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.jpa.dev.FlowGroup;
import com.jsofttechnologies.model.Credentials;
import com.jsofttechnologies.rexwar.util.WarConstants;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.util.PasswordHash;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.v2.services.FluidPlatformService;
import com.jsofttechnologies.v2.services.admin.UserService;
import com.jsofttechnologies.v2.services.resource.PageResource;
import com.jsofttechnologies.v2.util.Constants;
import com.jsofttechnologies.v2.util.UserInfo;
import com.jsofttechnologies.v2.util.WarToken;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Jerico on 26/08/2015.
 */
@Path("session/v2/login")
@Stateless(name = "v2/login")
@PermitAll
public class LoginService extends FluidPlatformService {


    @EJB
    private UserService userService;
    @EJB
    private UserModuleService userModuleService;
    @EJB(name = "v2/session")
    private SessionService sessionService;

    @POST
    @SkipCheck("authorization")
    @PermitAll
    public Response login(String loginKey) {
        Response response = null;

        byte[] byteKey = Base64.getDecoder().decode(loginKey);
        String[] login = new String(byteKey).split(":");
        String username = login[1];

        FlowUser user = null;

        if (username.matches(Constants.REGEX_EMAIL)) {
            user = userService.getType(userService.get(FlowUser.FIND_BY_EMAIL, new PageResource.QueryHelper().add("email").get(),
                    new PageResource.QueryHelper().add("string").get(), new PageResource.QueryHelper().add(username).get()));

        } else {
            user = userService.getType(userService.get(FlowUser.FIND_BY_USERNAME, new PageResource.QueryHelper().add("username").get(),
                    new PageResource.QueryHelper().add("string").get(), new PageResource.QueryHelper().add(username).get()));

        }

        if (user == null) {
            response = Response.serverError().status(Response.Status.UNAUTHORIZED).entity(messageService.getMessage("INVALID_LOGIN")).type(MediaType.TEXT_PLAIN_TYPE).build();
        } else {
            String password = login[2];
            try {
                boolean valid = PasswordHash.validatePassword(password, user.getPassword());
                if (!valid) {
                    response = Response.serverError().status(Response.Status.UNAUTHORIZED).entity(messageService.getMessage("INVALID_LOGIN")).type(MediaType.TEXT_PLAIN_TYPE).build();
                } else {
                    ObjectMapper mapper = new ObjectMapper();
                    Key key = new AesKey(context.getInitParameter("API_KEY").substring(0, 16).getBytes());
                    JsonWebEncryption jsonWebEncryption = new JsonWebEncryption();
                    UserInfo userInfo = new UserInfo();
                    WarToken warToken = new WarToken();
                    warToken.setCreated(new Date());
                    warToken.setUserId(user.getId());
                    warToken.setUsername(user.getUsername());
                    warToken.setGroup(user.getFlowUserGroup().getGroupName());
                    warToken.setFlowUserDetailId(user.getFlowUserDetail().getId());

                    List<FlowGroup> flowGroups = userModuleService.getSessionModules(warToken);
                    userInfo.setCreated(warToken.getCreated());
                    userInfo.setUserId(warToken.getUserId());
                    userInfo.setUsername(warToken.getUsername());
                    userInfo.setAvatarId(user.getFlowUserDetail().getAvatar());
                    userInfo.setGroup(warToken.getGroup());
                    userInfo.setFlowUserDetailId(warToken.getFlowUserDetailId());
                    userInfo.setFlowUserProfiles(user.getFlowUserProfileSet());
                    userInfo.setFlowGroups(flowGroups);

                    String json = mapper.writeValueAsString(warToken);
                    String userInfoJson = mapper.writeValueAsString(userInfo);
                    jsonWebEncryption.setPayload(json);
                    jsonWebEncryption.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
                    jsonWebEncryption.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
                    jsonWebEncryption.setKey(key);
                    String tokenized = jsonWebEncryption.getCompactSerialization();
                    sessionService.createSession(user.getId(), tokenized, request.getRemoteAddr());
                    response = Response.ok().status(Response.Status.ACCEPTED).entity("{\"token\":\"" + tokenized + "\", \"info\":" + userInfoJson + "}").type(MediaType.APPLICATION_JSON_TYPE).build();
                }
            } catch (Exception e) {
                ejbExceptionHandler.handleException(e, getClass());
            }
        }


        return response;
    }


    @Path("basic")
    @POST()
    @SkipCheck("authorization")
    @Consumes("application/json")
    @PermitAll
    public Response login(Credentials credentials) {

        Response response = null;
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        FlowUser user = userService.getUserByEmailOrUsername(username);

        if (user == null) {
            response = Response.serverError().status(Response.Status.UNAUTHORIZED).entity(messageService.getMessage("INVALID_LOGIN")).type(MediaType.TEXT_PLAIN_TYPE).build();
        } else {
            try {
                boolean valid = PasswordHash.validatePassword(password, user.getPassword());
                if (!valid) {
                    response = Response.serverError().status(Response.Status.UNAUTHORIZED).entity(messageService.getMessage("INVALID_LOGIN")).type(MediaType.TEXT_PLAIN_TYPE).build();
                } else {
                    ObjectMapper mapper = new ObjectMapper();
                    Key key = new AesKey(context.getInitParameter("API_KEY").substring(0, 16).getBytes());
                    JsonWebEncryption jsonWebEncryption = new JsonWebEncryption();
                    UserInfo userInfo = new UserInfo();
                    WarToken warToken = new WarToken();
                    warToken.setCreated(new Date());
                    warToken.setUserId(user.getId());
                    warToken.setUsername(user.getUsername());
                    warToken.setGroup(user.getFlowUserGroup().getGroupName());
                    warToken.setFlowUserDetailId(user.getFlowUserDetail().getId());

                    List<FlowGroup> flowGroups = userModuleService.getSessionModules(warToken);
                    userInfo.setCreated(warToken.getCreated());
                    userInfo.setUserId(warToken.getUserId());
                    userInfo.setUsername(warToken.getUsername());
                    userInfo.setAvatarId(user.getFlowUserDetail().getAvatar());
                    userInfo.setGroup(warToken.getGroup());
                    userInfo.setFlowUserDetailId(warToken.getFlowUserDetailId());
                    userInfo.setFlowUserProfiles(user.getFlowUserProfileSet());
                    userInfo.setFlowGroups(flowGroups);

                    String json = mapper.writeValueAsString(warToken);
                    String userInfoJson = mapper.writeValueAsString(userInfo);
                    jsonWebEncryption.setPayload(json);
                    jsonWebEncryption.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
                    jsonWebEncryption.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
                    jsonWebEncryption.setKey(key);
                    String tokenized = jsonWebEncryption.getCompactSerialization();
                    sessionService.createSession(user.getId(), tokenized, request.getRemoteAddr());
                    response = Response.ok().status(Response.Status.ACCEPTED).entity("{\"token\":\"" + tokenized + "\", \"info\":" + userInfoJson + "}").type(MediaType.APPLICATION_JSON_TYPE).build();
                }
            } catch (Exception e) {
                ejbExceptionHandler.handleException(e, getClass());
            }
        }


        return response;
    }
}
