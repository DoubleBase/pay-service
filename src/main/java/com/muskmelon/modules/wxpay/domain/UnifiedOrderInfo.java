package com.muskmelon.modules.wxpay.domain;

import com.muskmelon.common.annotion.Check;
import com.muskmelon.common.annotion.CheckForRequired;

/**
 * @author muskmelon
 * @description 统一下单入参信息
 * @date 2020-3-28 15:33
 * @since 1.0
 */
@CheckForRequired
public class UnifiedOrderInfo {

    /**
     * （必填）公众账号ID
     */
    @Check(required = true)
    private String appid;

    /**
     * （必填）商户号
     */
    @Check(required = true)
    private String mch_id;

    /**
     * （必填）设备号
     */
    @Check(required = true)
    private String device_info;

    /**
     * （必填）随机字符串
     */
    @Check(required = true)
    private String nonce_str;

    /**
     * （必填）签名
     */
    @Check(required = true)
    private String sign;

    /**
     * 签名类型
     */
    @Check(required = true)
    private String sign_type;

    /**
     * （必填）商品描述
     */
    @Check(required = true)
    private String body;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 附加数据
     */
    private String attach;

    /**
     * （必填）商户订单号
     */
    @Check(required = true)
    private String out_trade_no;

    /**
     * 货币类型
     */
    private String fee_type;

    /**
     * （必填）总金额，单位：分
     */
    @Check(required = true)
    private Integer total_fee;

    /**
     * （必填）终端IP
     */
    @Check(required = true)
    private String spbill_create_ip;

    /**
     * 交易起始时间 时间格式：yyyyMMddHHmmss
     */
    private String time_start;

    /**
     * 交易结束时间 时间格式：yyyyMMddHHmmss
     */
    private String time_expire;

    /**
     * 商品标记
     */
    private String goods_tag;

    /**
     * （必填）通知地址
     */
    @Check(required = true)
    private String notify_url;

    /**
     * （必填）交易类型
     */
    @Check(required = true)
    private String trade_type;

    /**
     * 商品ID
     */
    private String product_id;

    /**
     * 指定支付方式
     */
    private String limit_pay;

    /**
     * 用户标识
     */
    private String openid;

    /**
     * 电子发票入口开放标识
     */
    private String receipt;

    /**
     * （必填）场景信息
     */
    @Check(required = true)
    private String scene_info;

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

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getScene_info() {
        return scene_info;
    }

    public void setScene_info(String scene_info) {
        this.scene_info = scene_info;
    }
}
