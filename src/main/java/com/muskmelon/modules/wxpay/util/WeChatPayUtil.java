package com.muskmelon.modules.wxpay.util;

import com.google.common.collect.Maps;
import com.muskmelon.common.enums.SignType;
import com.muskmelon.common.util.HmacSHA256;
import com.muskmelon.common.util.MD5Util;
import com.muskmelon.common.util.MapXmlUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-28 16:15
 * @since 1.0
 */
public class WeChatPayUtil {

    private static final String FIELD_SIGN = "sign";

    /**
     * 生成带有sign的XML字符串
     * 签名方式MD5
     *
     * @param data
     * @param key
     * @return
     */
    public static String generateSignedXml(final Map<String, String> data, String key) throws Exception {
        return generateSignedXml(data, key, SignType.MD5);
    }

    /**
     * 生成带有sign的XML字符串
     *
     * @param data     map数据
     * @param key      API密钥
     * @param signType 签名类型
     * @return
     * @throws Exception
     */
    public static String generateSignedXml(final Map<String, String> data, String key, SignType signType) throws Exception {
        String sign = generateSignature(data, key, signType);
        data.put(FIELD_SIGN, sign);
        return MapXmlUtil.mapToXml(data);
    }

    /**
     * 生成签名，签名方式MD5
     *
     * @param data 待签名数据
     * @param key  API密钥
     * @return
     * @throws Exception
     */
    public static String generateSignature(final Map<String, String> data, String key) throws Exception {
        return generateSignature(data, key, SignType.MD5);
    }

    /**
     * 生成签名
     *
     * @param data     待签名数据
     * @param key      API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key, SignType signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[0]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(FIELD_SIGN)) {
                continue;
            }
            String value = data.get(k).trim();
            if (StringUtils.isNotBlank(value)) {
                // 参数值为空,不参与签名
                sb.append(k).append("=").append(value).append("&");
            }
        }
        sb.append("key=").append(key);
        if (SignType.MD5.equals(signType)) {
            return MD5Util.md5(sb.toString()).toUpperCase();
        } else if (SignType.HMACSHA256.equals(signType)) {
            return HmacSHA256.hmacSha256(sb.toString(), key).toUpperCase();
        } else {
            throw new Exception(String.format("Invalid signType: %s", signType));
        }
    }

    /**
     * 验证签名是否正确
     *
     * @param data map格式数据
     * @param key  API密钥
     * @return
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
        return isSignatureValid(data, key, SignType.MD5);
    }

    /**
     * 验证签名是否正确
     *
     * @param data     map格式数据
     * @param key      API密钥
     * @param signType 签名方式
     * @return
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key, SignType signType) throws Exception {
        if (!data.containsKey(FIELD_SIGN)) {
            return false;
        }
        String sign = data.get(FIELD_SIGN);
        return generateSignature(data, key, signType).equals(sign);
    }

    /**
     * 校验签名是否正确
     *
     * @param xmlStr xml格式数据
     * @param key    API密钥
     * @return
     */
    public static boolean isSignatureValid(String xmlStr, String key) throws Exception {
        return isSignatureValid(xmlStr, key, SignType.MD5);
    }

    /**
     * 校验签名是否正确
     *
     * @param xmlStr xml格式数据
     * @param key    API密钥
     * @return
     */
    public static boolean isSignatureValid(String xmlStr, String key, SignType signType) throws Exception {
        Map<String, String> data = MapXmlUtil.xmlToMap(xmlStr);
        return isSignatureValid(data, key, signType);
    }

    /**
     * 获取随机字符串
     *
     * @return
     */
    public static String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    /**
     * 格式化请求返回结果
     *
     * @param resultMap
     */
    public static Map<String, String> convertResult(Map<String, String> resultMap) {
        Map<String, String> map = Maps.newHashMap();
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            String value = convertCDATA(entry.getValue());
            map.put(entry.getKey(), value);
        }
        return map;
    }

    /**
     * 提取<![CDATA[xxx]]>格式中的数据
     *
     * @param data 待提取的数据
     * @return
     */
    public static String convertCDATA(String data) {
        return data.replace("<![CDATA[", "").replace("]]>", "");
    }

    /**
     * 将数据组装进<![CDATA[]]>中
     * @param data
     * @return
     */
    public static String appendCDATA(String data){
        return "<![CDATA[" + data + "]]>";
    }


}
