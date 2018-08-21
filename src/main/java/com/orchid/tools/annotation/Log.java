package com.orchid.tools.annotation;

import java.lang.annotation.*;

/**
 * Created by ljg on 2018/5/3.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE})
public @interface Log {
    String title() default "";
    String type() default "";
}
