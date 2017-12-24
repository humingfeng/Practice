package com.practice.manage.target;

import java.lang.annotation.*;

/**
 * @author Xushd  2017/12/24 23:19
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerPermisson {

    String value()  default "";

}
