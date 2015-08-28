package com.jsofttechnologies.filters;

import com.jsofttechnologies.v2.util.Constants;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.Key;

/**
 * Created by Jerico on 31/07/2015.
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/services/v2/*"})
public class AuthFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;


        String query = ((HttpServletRequest) servletRequest).getQueryString();
        String requestToken = null;
        if (query != null && query.contains("token=")) {
            String[] uris = query.split("token=");

            String uri = uris[0];

            if (!uri.isEmpty() && uri.charAt(uri.length() - 1) == '?') {
                uri = uri.replace("?", "");
            }
            requestToken = uris[1];
        }
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null || requestToken != null) {
            String token = null;
            if (authHeader != null) {
                token = ((HttpServletRequest) servletRequest).getHeader("Authorization").replace("bearer ", "");
            } else {
                token = requestToken;
            }

            if (token != null) {
                Key key = new AesKey(Constants.API_KEY.substring(0, 16).getBytes());
                JsonWebEncryption jsonWebEncryption = new JsonWebEncryption();
                jsonWebEncryption.setKey(key);
                try {
                    jsonWebEncryption.setCompactSerialization(token);
                    String payload = jsonWebEncryption.getPayload();
                    servletRequest.setAttribute("info", payload);
                } catch (JoseException e) {
                    e.printStackTrace();
                }
            } else {
                ((HttpServletResponse) servletResponse).setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            }
        } else {
            ((HttpServletResponse) servletResponse).setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            ((HttpServletResponse) servletResponse).sendError(Response.Status.UNAUTHORIZED.getStatusCode());
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
    }
}
