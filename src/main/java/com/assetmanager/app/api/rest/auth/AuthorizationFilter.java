package com.assetmanager.app.api.rest.auth;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.app.model.entity.UserRole;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    @JwtSecured
    @Provider
    @Priority(Priorities.AUTHORIZATION)
    public class AuthorizationFilter implements ContainerRequestFilter {

        @Context
        private ResourceInfo resourceInfo;

        @Inject
        @AuthenticatedUser
        User authenticatedUser;

        @Override
        public void filter(ContainerRequestContext requestContext) throws IOException {

            Class<?> resourceClass = resourceInfo.getResourceClass();
            List<UserRole> classRoles = extractRoles(resourceClass);

            Method resourceMethod = resourceInfo.getResourceMethod();
            List<UserRole> methodRoles = extractRoles(resourceMethod);

            try {
                if (resourceMethod.isAnnotationPresent(DenyAll.class)) {
                    requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                            .entity("Endpoint Not Accessible for all users").build());
                    return;
                }

                if (methodRoles.isEmpty()) {
                    checkPermissions(classRoles);
                } else {
                    checkPermissions(methodRoles);
                }
            } catch (Exception e) {
                requestContext.abortWith(
                        Response.status(Response.Status.FORBIDDEN).build());
            }
        }

        private List<UserRole> extractRoles(AnnotatedElement annotatedElement) {
            if (annotatedElement == null) {
                return new ArrayList<UserRole>();
            } else {
                JwtSecured secured = annotatedElement.getAnnotation(JwtSecured.class);
                if (secured == null) {
                    return new ArrayList<UserRole>();
                } else {
                    UserRole[] allowedRoles = secured.value();
                    return Arrays.asList(allowedRoles);
                }
            }
        }

        private void checkPermissions(List<UserRole> allowedRoles) throws Exception {
            UserRole userRole = authenticatedUser.getUserRole();

            if (!allowedRoles.contains(userRole)) {
                throw new ForbiddenException("Insufficient permissions to access resource");
            }
        }
    }

