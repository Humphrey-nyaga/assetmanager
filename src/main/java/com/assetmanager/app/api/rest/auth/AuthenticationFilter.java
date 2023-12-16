package com.assetmanager.app.api.rest.auth;

import com.assetmanager.app.bean.UserBeanI;
import com.assetmanager.app.model.entity.User;
import com.assetmanager.auth.JWTUtil;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@JwtSecured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Inject
    @AuthenticatedUser
    Event<User> userAuthenticatedEvent;

    @EJB
    UserBeanI userBean;

    @Context
    private ResourceInfo resourceInfo;
    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Inject
    JWTUtil jwtUtil;
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abort(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            validateToken(token);

        } catch (Exception e) {
            abort(requestContext);
        }

    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abort(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }

    private void validateToken(String token) throws Exception {

        String username = jwtUtil.extractUsername(token);
        User user = userBean.findUserByUsername(username);
        if(user!=null){
            if(jwtUtil.isTokenValid(token,user)){
                userAuthenticatedEvent.fire(user);
            }
        }

        //jwtUtil.isTokenValid();
    }


}
