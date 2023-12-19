package com.assetmanager.app.api.rest.auth;


import com.assetmanager.app.model.entity.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;

@RequestScoped
public class UserAuthenticatedEvent {

    @Produces
    @RequestScoped
    @AuthenticatedUser
    private User authenticatedUser;

    public void handleAuthenticationEvent(@Observes @AuthenticatedUser User user) {
        this.authenticatedUser = user;
    }
}