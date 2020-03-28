package com.muskmelon.common.util;

import com.muskmelon.common.constants.SaltConstant;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-28 16:50
 * @since 1.0
 */
public class HmacSHA256 {

    /**
     * 生成HMACSHA256
     * @param data 待处理数据
     * @param key 密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String hmacSha256(String data, String key) throws Exception {
        Mac sha256 = Mac.getInstance(SaltConstant.HMACSHA256);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), SaltConstant.HMACSHA256);
        sha256.init(secretKeySpec);
        byte[] array = sha256.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString().toUpperCase();
    }
}
