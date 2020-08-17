package com.developcollect.commonpay.balancesample.config.commonpay;

import com.developcollect.commonpay.balancesample.service.IBalanceService;
import com.developcollect.commonpay.config.DefaultPayFactory;
import com.developcollect.commonpay.pay.Pay;
import com.developcollect.commonpay.pay.nopay.NoPay;
import lombok.RequiredArgsConstructor;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/17 9:33
 */
@RequiredArgsConstructor
public class LocalPayFactory extends DefaultPayFactory {

    private final IBalanceService balanceService;

    @Override
    public Pay createPay(int payPlatform) {
        // 如果不是自定义的支付平台, 那么使用父类的创建方法
        if (payPlatform != LocalPayPlatform.BALANCE_PAY) {
            return super.createPay(payPlatform);
        }

        // 下面才是自己扩展的支付
        return payMap.computeIfAbsent(payPlatform, k -> {
            switch (k) {
                case LocalPayPlatform.BALANCE_PAY:
                    return new BalancePay(balanceService);
                default:
                    return new NoPay();
            }
        });
    }
}
