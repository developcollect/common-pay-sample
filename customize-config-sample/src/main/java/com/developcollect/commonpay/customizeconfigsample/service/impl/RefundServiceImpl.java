package com.developcollect.commonpay.customizeconfigsample.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.developcollect.commonpay.autoconfig.RefundEvent;
import com.developcollect.commonpay.customizeconfigsample.entity.LocalOrder;
import com.developcollect.commonpay.customizeconfigsample.entity.LocalRefund;
import com.developcollect.commonpay.customizeconfigsample.service.IOrderService;
import com.developcollect.commonpay.customizeconfigsample.service.IRefundService;
import com.developcollect.commonpay.pay.RefundResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/10 14:29
 */
@Slf4j
@Service
public class RefundServiceImpl implements IRefundService {

    @Autowired
    private IOrderService orderService;

    @Override
    public LocalRefund createRefund(String orderCode, long amount) {
        LocalOrder order = orderService.getOrder(orderCode);
        if (amount > order.getTotalFee()) {
            throw new IllegalArgumentException("退款金额超出实付金额");
        }
        LocalRefund localRefund = new LocalRefund();
        localRefund.setCode(RandomUtil.randomString(9));
        localRefund.setRefundAmount(amount);
        localRefund.setPayType(order.getPayType());
        return localRefund;
    }


    @EventListener(RefundEvent.class)
    private void test(RefundEvent payEvent) {
        RefundResponse refundResponse = payEvent.getRefundResponse();
        log.info("退款单[{}]退款成功: [{}]", refundResponse.getOutRefundNo(), refundResponse);
    }
}
