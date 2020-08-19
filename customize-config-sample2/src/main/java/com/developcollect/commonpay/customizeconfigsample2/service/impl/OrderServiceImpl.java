package com.developcollect.commonpay.customizeconfigsample2.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.developcollect.commonpay.autoconfig.PayEvent;
import com.developcollect.commonpay.customizeconfigsample2.entity.LocalOrder;
import com.developcollect.commonpay.customizeconfigsample2.service.IOrderService;
import com.developcollect.commonpay.pay.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/7 16:28
 */
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    private Map<String, LocalOrder> localOrderMap = new ConcurrentHashMap<>();

    @Override
    public LocalOrder getOrder() {
        LocalOrder localOrder;
        do {
            localOrder = randomOrder();
        } while (localOrderMap.containsKey(localOrder.getCode()));

        localOrderMap.put(localOrder.getCode(), localOrder);
        return localOrder;
    }

    @Override
    public LocalOrder getOrder(String code) {
        return localOrderMap.get(code);
    }

    @Override
    public LocalOrder getOrderSpecifyAmount(long amount) {
        LocalOrder order = getOrder();
        order.setTotalFee(amount);
        return order;
    }

    private LocalOrder randomOrder() {
        LocalOrder localOrder = new LocalOrder();
        localOrder.setCode(RandomUtil.randomString(9));
        localOrder.setTotalFee(RandomUtil.randomLong(1, 99999));
        LocalDateTime now = LocalDateTime.now();
        localOrder.setTimeStart(now.minusMinutes(10));
        localOrder.setTimeExpire(now.plusMinutes(111));
        return localOrder;
    }

    @EventListener(PayEvent.class)
    private void test(PayEvent payEvent) {
        PayResponse payResponse = payEvent.getPayResponse();

        LocalOrder localOrder = localOrderMap.get(payResponse.getOutTradeNo());
        if (localOrder != null) {
            localOrder.setPayType(payResponse.getPayPlatform());
        }

        log.info("订单[{}]支付成功: [{}]", payResponse.getOutTradeNo(), payResponse);
    }
}
