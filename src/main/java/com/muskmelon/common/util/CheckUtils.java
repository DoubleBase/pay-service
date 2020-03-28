package com.muskmelon.common.util;

import com.google.common.collect.Lists;
import com.muskmelon.common.annotion.Check;
import com.muskmelon.common.annotion.CheckForRequired;
import com.muskmelon.common.exception.CheckException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-28 23:14
 * @since 1.0
 */
public class CheckUtils {

    /**
     * 校验对象参数是否必填
     *
     * @param object
     * @return
     */
    public static void checkParams(Object object) throws IllegalAccessException {
        Class<?> cls = object.getClass();
        CheckForRequired checkForRequired = cls.getAnnotation(CheckForRequired.class);
        if (checkForRequired == null) {
            return;
        }
        Field[] fields = cls.getDeclaredFields();
        List<String> checkFields = Lists.newArrayList();
        for (Field field : fields) {
            Check check = field.getAnnotation(Check.class);
            if (check == null || !check.required()) {
                continue;
            }
            field.setAccessible(true);
            Object obj = field.get(object);
            if (check.required()) {
                if (null == obj) {
                    checkFields.add(field.getName());
                } else if (field.getType().equals(String.class)) {
                    if (StringUtils.isBlank(String.valueOf(obj))) {
                        checkFields.add(field.getName());
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(checkFields)) {
            String errorMsg = String.format("参数校验失败,以下字段必填：%s", Arrays.toString(checkFields.toArray()));
            throw new CheckException(errorMsg);
        }
    }

}
