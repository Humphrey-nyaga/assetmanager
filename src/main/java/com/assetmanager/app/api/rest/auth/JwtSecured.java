package com.assetmanager.app.api.rest.auth;

import com.assetmanager.app.model.entity.UserRole;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.Boolean.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NameBinding
@Retention(RUNTIME)
@Target({ElementType.TYPE, METHOD})
public @interface JwtSecured {
    UserRole[] value() default {};

}