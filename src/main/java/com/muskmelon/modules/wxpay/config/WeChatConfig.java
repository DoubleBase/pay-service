package com.muskmelon.modules.wxpay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-29 23:00
 * @since 1.0
 */
@Component
@PropertySource(value = {"classpath:/application-wechat.properties"})
public class WeChatConfig {

    /**
     * appId
     */
    @Value("${appId}")
    private String appId;

    /**
     * 商户号
     */
    @Value("${mchId}")
    private String mchId;

    /**
     * API密钥
     */
    @Value("${key}")
    private String key;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
