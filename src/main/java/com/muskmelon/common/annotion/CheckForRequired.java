package com.muskmelon.common.annotion;

import java.lang.annotation.*;

/**
 * @author muskmelon
 * @description 对象字段是否进行校验注解
 * @date 2020-3-28 23:12
 * @since 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckForRequired {
}
