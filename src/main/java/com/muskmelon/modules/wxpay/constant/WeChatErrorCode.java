package com.muskmelon.modules.wxpay.constant;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-28 15:52
 * @since 1.0
 */
public enum WeChatErrorCode {

    NOAUTH("商户无此接口权限"),

    NOTENOUGH("余额不足"),

    ORDERPAID("商户订单已支付"),

    ORDERCLOSED("订单已关闭"),

    SYSTEMERROR("系统错误"),

    APPID_NOT_EXIST("APPID不存在"),

    MCHID_NOT_EXIST("MCHID不存在"),

    APPID_MCHID_NOT_MATCH("appid和mch_id不匹配"),

    LACK_PARAMS("缺少参数"),

    OUT_TRADE_NO_USED("商户订单号重复"),

    SIGNERROR("签名错误"),

    XML_FORMAT_ERROR("XML格式错误"),

    REQUIRE_POST_METHOD("请使用post方法"),

    POST_DATA_EMPTY("post数据为空"),

    NOT_UTF8("编码格式错误");

    /**
     * 错误描述
     */
    private String description;

    WeChatErrorCode(String description) {
        this.description = description;
    }
}
