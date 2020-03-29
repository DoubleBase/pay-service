package com.muskmelon.modules.wxpay.service.impl;

/**
 * @author muskmelon
 * @description 域名管理, 实现主备域名自动切换
 * @date 2020-3-29 21:49
 * @since 1.0
 */
public abstract class WeChatDomainService {

    /**
     * 上报域名网络状况
     *
     * @param domain   域名：api.mch.weixin.qq.com
     * @param costTime 耗时
     * @param e        网络请求中出现异常
     *                 null：没有异常
     *                 ConnectTimeoutException：建立网络连接异常
     *                 UnknowHostException：dns解析异常
     */
    abstract void report(final String domain, long costTime, final Exception e);

    abstract DomainInfo getDomain();

    class DomainInfo {
        /**
         * 域名
         */
        public String domain;
        /**
         * 该域名是否为主域名
         */
        public boolean primaryDomain;

        public DomainInfo(String domain, boolean primaryDomain) {
            this.domain = domain;
            this.primaryDomain = primaryDomain;
        }

        @Override
        public String toString() {
            return "DomainInfo{" +
                    "domain='" + domain + '\'' +
                    ", primaryDomain=" + primaryDomain +
                    '}';
        }
    }

}
