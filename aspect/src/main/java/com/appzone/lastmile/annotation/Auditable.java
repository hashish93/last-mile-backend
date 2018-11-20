package com.appzone.lastmile.annotation;

import com.appzone.lastmile.model.Action;
import com.appzone.lastmile.model.Screen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Appzone on 14-Feb-17.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditable {

    String NULL = "###";

    Action action();
    Screen screenName();
    String [] fieldNames() default NULL;
}
