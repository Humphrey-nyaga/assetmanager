package com.assetmanager.app.view.html;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlFormField {
    String label() default "";

    String name() default "";
    String id() default "";
    boolean isRequired() default false;

}
