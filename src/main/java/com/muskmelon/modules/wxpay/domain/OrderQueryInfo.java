package com.muskmelon.modules.wxpay.domain;

import com.muskmelon.common.annotion.Check;
import com.muskmelon.common.annotion.CheckForRequired;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-29 21:16
 * @since 1.0
 */
@CheckForRequired
public class OrderQueryInfo {

    /**
     * 公众账号ID
     */
    @Check(required = true)
    private String appid;

    /**
     * 商户号
     */
    @Check(required = true)
    private String mch_id;

    /**
     * 微信订单号 与商户订单号 二选一 必填
     */
    private String transaction_id;

    /**
     * 商户订单号 与微信订单号 二选一 必填
     */
    private String out_trade_no;

    /**
     * 随机字符串
     */
    @Check(required = true)
    private String nonce_str;

    /**
     * 签名
     */
    @Check(required = true)
    private String sign;

    /**
     * 签名类型
     */
    private String sign_type;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }
}
