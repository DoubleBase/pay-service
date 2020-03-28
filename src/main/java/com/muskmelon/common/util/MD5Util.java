package com.muskmelon.common.util;

import com.muskmelon.common.constants.SaltConstant;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-28 16:31
 * @since 1.0
 */
public class MD5Util {

    /**
     * md5加密
     *
     * @param data 待加密数据
     * @return
     */
    public static String md5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance(SaltConstant.MD5);
        byte[] array = md.digest(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }


}
