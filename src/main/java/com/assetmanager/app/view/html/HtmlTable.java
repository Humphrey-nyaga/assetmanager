package com.assetmanager.app.view.html;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlTable {
    String name();
    String label() default "";
    String addUrl() default "";
    String url() default "";
    String updateUrl() default "";
}
