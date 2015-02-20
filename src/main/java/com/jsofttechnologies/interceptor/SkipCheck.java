package com.jsofttechnologies.interceptor;

import java.lang.annotation.*;

/**
 * Created by Jerico on 1/2/2015.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SkipCheck {
    String value() default "action";
}
