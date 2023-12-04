package com.assetmanager.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Implemented by a single class that validates the annotation
 * */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YearValidator.class)
public @interface ValidYear {
    String message() default "Year must be 4 digits long and between 1900 and the current year.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
