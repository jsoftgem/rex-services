package com.jsofttechnologies.v2.services.session;

import com.google.gson.Gson;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.util.PasswordHash;
import com.jsofttechnologies.v2.services.FluidPlatformService;
import com.jsofttechnologies.v2.services.admin.UserService;
import com.jsofttechnologies.v2.services.resource.PageResource;
import com.jsofttechnologies.v2.util.Constants;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Base64;

/**
 * Created by Jerico on 26/08/2015.
 */
@Path("session/v2/login")
@Stateless(name = "v2/login")
public class LoginService extends FluidPlatformService {


    @EJB
    private UserService userService;

    @POST
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
            response = Response.serverError().status(Response.Status.UNAUTHORIZED).entity(messageService.getMessage("INVALID_LOGIN")).build();
        } else {
            String password = login[2];
            try {
                boolean valid = PasswordHash.validatePassword(password, user.getPassword());
                if (!valid) {
                    response = Response.serverError().status(Response.Status.UNAUTHORIZED).entity(messageService.getMessage("INVALID_LOGIN")).build();
                } else {

                    Key key = new AesKey(context.getInitParameter("API_KEY").substring(0, 16).getBytes());
                    JsonWebEncryption jsonWebEncryption = new JsonWebEncryption();
                    Gson gson = new Gson();
                    String json = gson.toJson(user.getFlowUserDetail());
                    jsonWebEncryption.setPayload(json);
                    jsonWebEncryption.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
                    jsonWebEncryption.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
                    jsonWebEncryption.setKey(key);

                    response = Response.ok().status(Response.Status.ACCEPTED).entity("{\"token\":\"" + jsonWebEncryption.getCompactSerialization() + "\", \"info\":" + json + "}").type(MediaType.APPLICATION_JSON_TYPE).build();
                }
            } catch (Exception e) {
                ejbExceptionHandler.handleException(e, getClass());
            }
        }


        return response;
    }
}
