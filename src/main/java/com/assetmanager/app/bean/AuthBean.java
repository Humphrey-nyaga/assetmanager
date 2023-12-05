package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.User;
import com.assetmanager.app.model.entity.UserRole;
import com.assetmanager.exceptions.UserPasswordEncodingException;
import com.assetmanager.util.security.PasswordEncoderI;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Stateless
@Local
public class AuthBean implements AuthBeanI, Serializable {
    @Inject
    PasswordEncoderI passwordEncoder;
    @PersistenceContext
    EntityManager em;

    @Override
    public User authenticate(User userToAuthenticate) {

        try {
            userToAuthenticate.setPassword(passwordEncoder.encodePassword(userToAuthenticate.getPassword()));

            return em.createQuery("FROM User u WHERE u.password=:password AND u.username=:username", User.class)
                    .setParameter("password", userToAuthenticate.getPassword())
                    .setParameter("username", userToAuthenticate.getUsername()).getSingleResult();
        } catch (NoSuchAlgorithmException e) {
            throw new UserPasswordEncodingException("Password decoding algorithm failed: " + e.getMessage());
        } catch (NoResultException e) {
            throw new RuntimeException("Invalid user Logins!!");
        }

    }
}

