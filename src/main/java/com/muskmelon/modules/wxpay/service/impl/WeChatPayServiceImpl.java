package com.muskmelon.modules.wxpay.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.muskmelon.common.exception.CheckException;
import com.muskmelon.common.util.CheckUtils;
import com.muskmelon.common.util.HttpClientUtils;
import com.muskmelon.common.util.MapXmlUtil;
import com.muskmelon.modules.wxpay.constant.WeChatConstants;
import com.muskmelon.modules.wxpay.domain.OrderQueryInfo;
import com.muskmelon.modules.wxpay.domain.UnifiedOrderInfo;
import com.muskmelon.modules.wxpay.dto.UnifiedOrderResult;
import com.muskmelon.modules.wxpay.service.WeChatPayService;
import com.muskmelon.modules.wxpay.util.WeChatPayUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-28 0:12
 * @since 1.0
 */
@Service
public class WeChatPayServiceImpl implements WeChatPayService {

    private static Logger logger = LoggerFactory.getLogger(WeChatPayServiceImpl.class);

    @Override
    public void unifiedOrder(UnifiedOrderInfo unifiedOrderInfo) {
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        try {
            // 校验参数
            CheckUtils.checkParams(unifiedOrderInfo);
            Map<String, String> params = BeanUtils.describe(unifiedOrderInfo);
            String resultStr = HttpClientUtils.doPost(url, params);
            Map<String, String> resultMap = MapXmlUtil.xmlToMap(resultStr);
            UnifiedOrderResult result = new UnifiedOrderResult();
            BeanUtils.populate(result, WeChatPayUtil.convertResult(resultMap));
            if (WeChatConstants.SUCCESS.equals(result.getReturn_code())) {
                logger.info("统一下单接口调用成功,返回结果：{}", JSONArray.toJSONString(result));
            } else {
                logger.error("统一下单接口调用失败,错误码：{},错误描述：{}", result.getErr_code(), result.getErr_code_des());
            }
        } catch (CheckException e) {
            logger.error(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error("字段校验失败", e);
        } catch (Exception e) {
            logger.error("统一下单失败!", e);
        }
    }

    @Override
    public void orderQuery(OrderQueryInfo orderQueryInfo) {
        String url = "https://api.mch.weixin.qq.com/pay/orderquery";


    }

}
