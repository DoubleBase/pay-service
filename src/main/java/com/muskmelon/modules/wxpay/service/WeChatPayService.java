package com.muskmelon.modules.wxpay.service;

import com.muskmelon.modules.wxpay.domain.OrderQueryInfo;
import com.muskmelon.modules.wxpay.domain.UnifiedOrderInfo;

/**
 * @author muskmelon
 * @description 微信支付服务
 * @date 2020-3-28 0:11
 * @since 1.0
 */
public interface WeChatPayService {

    /**
     * 统一下单
     * @param unifiedOrderInfo 入参
     */
    void unifiedOrder(UnifiedOrderInfo unifiedOrderInfo);

    /**
     * 查询订单
     * @param orderQueryInfo 入参
     */
    void orderQuery(OrderQueryInfo orderQueryInfo);

}
