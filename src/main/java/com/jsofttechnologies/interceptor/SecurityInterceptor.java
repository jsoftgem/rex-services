package com.jsofttechnologies.interceptor;

import com.jsofttechnologies.ejb.FlowUserManager;
import com.jsofttechnologies.ejb.MergeExceptionSummary;
import com.jsofttechnologies.jpa.admin.FlowUser;
import com.jsofttechnologies.security.SessionHelper;
import com.jsofttechnologies.services.admin.FlowUserQueryService;
import com.jsofttechnologies.services.util.FlowPermissionService;
import com.jsofttechnologies.services.util.FlowSessionHelper;
import com.jsofttechnologies.services.util.MessageService;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.util.ProjectHelper;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Provider
@ServerInterceptor
public class SecurityInterceptor implements PreProcessInterceptor {
    @EJB
    private SessionHelper sessionHelper;

    @EJB
    private FlowUserManager flowUserManager;

    @EJB
    private MergeExceptionSummary exceptionSummary;

    @EJB
    private FlowPermissionService flowPermissionService;

    @EJB
    private MessageService messageService;

    @EJB
    private FlowUserQueryService flowUserQueryService;

    @EJB
    private FlowSessionHelper flowSessionHelper;

    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", ProjectConstants.STATUS_NOT_AUTHENTICATED, new Headers<Object>());
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", ProjectConstants.STATUS_NOT_AUTHORIZED, new Headers<Object>());
    private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<Object>());


    public ServerResponse preProcess(HttpRequest request, ResourceMethodInvoker methodInvoked) throws Failure, WebApplicationException {
        Method method = methodInvoked.getMethod();
        Map<String, Object> jsonMap = new HashMap<>();

        //Access allowed for all
        if (method.isAnnotationPresent(PermitAll.class)) {
            return null;
        }
        //Access denied for all
        if (method.isAnnotationPresent(DenyAll.class)) {
            return ACCESS_FORBIDDEN;
        }

        SkipCheck skipCheck = null;

        if (method.isAnnotationPresent(SkipCheck.class)) {
            skipCheck = method.getAnnotation(SkipCheck.class);
        }

        //Get request headers
        final HttpHeaders headers = request.getHttpHeaders();

        //Fetch flowPage Header
        final String flowPage = headers.getHeaderString(ProjectConstants.HEADER_FLOWPAGE);
        final String httpMethod = request.getHttpMethod();
        //Fetch authorization header
        final List<String> authorization = headers.getRequestHeader(ProjectConstants.HEADER_AUTHORIZATION);


        boolean skipAuthorization = skipCheck != null && skipCheck.value().equals("authorization");
        boolean skipAuthentication = skipCheck != null && skipCheck.value().equals("authentication");
        boolean skipAction = skipCheck != null && skipCheck.value().equals("action");

        if (skipAuthorization) return null;

        //If no authorization information present; block access
        if ((authorization == null || authorization.isEmpty()) && !skipAuthorization) {
            jsonMap.put("msg", messageService.getMessage(ProjectConstants.MSG_LOGIN_REQUIRED));
            ACCESS_DENIED.setEntity(ProjectHelper.json(jsonMap));
            return ACCESS_DENIED;
        }

        //Get encoded username and password
        final String sessionKey = authorization.get(0).replaceFirst(ProjectConstants.AUTHENTICATION_SCHEME + " ", "");


        if ((!skipAuthorization && !skipAuthentication)) {
            if (!isAuthenticated(sessionKey)) {
                jsonMap.put("msg", messageService.getMessage(ProjectConstants.MSG_AUTH_FAILED));
                ACCESS_DENIED.setEntity(ProjectHelper.json(jsonMap));
                return ACCESS_DENIED;
            }
        }
        if (flowPage != null) {
            if ((!skipAuthorization && !skipAuthentication) && !skipAction) {
                if (!flowPermissionService.hasPermission(authorization.get(0), flowPage, httpMethod)) {
                    jsonMap.put("msg", messageService.getMessage(ProjectConstants.MSG_ACCESS_DENIED_ACTION));
                    ACCESS_FORBIDDEN.setEntity(ProjectHelper.json(jsonMap));
                    return ACCESS_FORBIDDEN;
                }
            }
        }

        //Return null to continue request processing
        return null;
    }


    private boolean isAuthenticated(String username, String password) {
        try {
            FlowUser flowUser = flowUserQueryService.getFlowUserByUsername(username);
            if (flowUser != null) return true;
           /* TODO: return PasswordHash.validatePassword(password, flowUser.getPassword());*/
            return false;

      /*  } catch (NoSuchAlgorithmException e) {
            exceptionSummary.handleException(e, getClass());
        } catch (InvalidKeySpecException e) {
            exceptionSummary.handleException(e, getClass());
        */
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass());
        }

        return false;
    }


    private boolean isAuthenticated(String sessionKey) {
        try {
            FlowSessionHelper.Promise promise = flowSessionHelper.isAuthorized(sessionKey);
            return promise.getOk();
        } catch (Exception e) {
            exceptionSummary.handleException(e, getClass(), sessionKey);
        }

        return false;
    }

}