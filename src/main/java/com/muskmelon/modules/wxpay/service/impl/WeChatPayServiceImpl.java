package com.muskmelon.modules.wxpay.service.impl;

import com.muskmelon.common.annotion.Check;
import com.muskmelon.common.annotion.CheckForRequired;
import com.muskmelon.common.exception.CheckException;
import com.muskmelon.common.util.CheckUtils;
import com.muskmelon.common.util.HttpClientUtils;
import com.muskmelon.modules.wxpay.domain.UnifiedOrderInfo;
import com.muskmelon.modules.wxpay.service.WeChatPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.AnnotatedType;
import java.util.HashMap;
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

//            Map<String, String> params = new HashMap<>();
//
//            String result = HttpClientUtils.doPost(url, params);

        } catch (CheckException e) {
            logger.error(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error("字段校验失败", e);
        }
    }


}
