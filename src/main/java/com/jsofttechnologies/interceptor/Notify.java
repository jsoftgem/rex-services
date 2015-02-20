package com.jsofttechnologies.interceptor;

import com.jsofttechnologies.jpa.util.FlowAlertType;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

/**
 * Created by Jerico on 12/29/2014.
 */
@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.CONSTRUCTOR})
public @interface Notify {
    String task() default "notification";

    String page() default "notification_view";

    FlowAlertType alertType() default FlowAlertType.SILENT;
}
