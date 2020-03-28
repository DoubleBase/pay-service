package com.muskmelon.common.annotion;

import java.lang.annotation.*;

/**
 * @author muskmelon
 * @description 字段校验注解
 * @date 2020-3-28 23:03
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Check {

    /**
     * 必填属性,默认非必填
     */
    boolean required() default false;

}
