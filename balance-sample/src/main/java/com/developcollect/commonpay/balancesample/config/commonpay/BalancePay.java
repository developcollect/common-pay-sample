package com.developcollect.commonpay.balancesample.config.commonpay;

import com.developcollect.commonpay.balancesample.entity.BalancePayRecord;
import com.developcollect.commonpay.balancesample.entity.LocalOrder;
import com.developcollect.commonpay.balancesample.service.IBalanceService;
import com.developcollect.commonpay.pay.AbstractPay;
import com.developcollect.commonpay.pay.IOrder;
import com.developcollect.commonpay.pay.PayResponse;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2020/8/17 9:30
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@RequiredArgsConstructor
public class BalancePay extends AbstractPay {

    private final IBalanceService balanceService;

    @Override
    public PayResponse paySync(IOrder order) {
        // 1. 获取本地订单对象
        Object source = order.getSource();
        if (!(source instanceof LocalOrder)) {
            throw new IllegalArgumentException("订单类错误");
        }
        LocalOrder localOrder = (LocalOrder) source;

        // 2. 调用本地余额支付方法
        BalancePayRecord payRecord = balanceService.pay(localOrder);

        // 3. 封装PayResponse对象
        PayResponse payResponse = new PayResponse()
                .setPayPlatform(LocalPayPlatform.BALANCE_PAY)
                .setSuccess(true)
                .setTradeNo(payRecord.getId().toString())
                .setOutTradeNo(order.getOutTradeNo())
                .setPayTime(LocalDateTime.now())
                .setRawObj(payRecord);

        return payResponse;
    }

    @Override
    protected int getPlatform() {
        return LocalPayPlatform.BALANCE_PAY;
    }
}
