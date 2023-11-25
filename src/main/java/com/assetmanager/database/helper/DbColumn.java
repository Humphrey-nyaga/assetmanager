package com.assetmanager.database.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DbColumn {
    String name() default"";

    String definition() default "VARCHAR(255)";
  //  String isNullable() default "NOT NULL";
}
