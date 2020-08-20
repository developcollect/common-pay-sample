package com.developcollect.commonpay.balancesample.config.commonpay;

import com.developcollect.commonpay.balancesample.entity.BalancePayRecord;
import com.developcollect.commonpay.balancesample.entity.LocalOrder;
import com.developcollect.commonpay.balancesample.service.IBalanceService;
import com.developcollect.commonpay.pay.AbstractPay;
import com.developcollect.commonpay.pay.IPayDTO;
import com.developcollect.commonpay.pay.PayResponse;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/17 9:30
 */
@RequiredArgsConstructor
public class BalancePay extends AbstractPay {

    private final IBalanceService balanceService;

    @Override
    public PayResponse paySync(IPayDTO payDTO) {
        // 1. 获取本地订单对象
        Object source = payDTO.getSource();
        if (!(source instanceof LocalOrder)) {
            throw new IllegalArgumentException("订单类错误");
        }
        LocalOrder localOrder = (LocalOrder) source;

        // 2. 调用本地余额支付方法
        String password = payDTO.getExt("PAY_CIPHER");
        BalancePayRecord payRecord = balanceService.pay(localOrder, password);

        // 3. 封装PayResponse对象
        PayResponse payResponse = new PayResponse()
                .setPayPlatform(LocalPayPlatform.BALANCE_PAY)
                .setSuccess(true)
                .setTradeNo(payRecord.getId().toString())
                .setOutTradeNo(payDTO.getOutTradeNo())
                .setPayTime(LocalDateTime.now())
                .setRawObj(payRecord);

        return payResponse;
    }

    @Override
    protected int getPlatform() {
        return LocalPayPlatform.BALANCE_PAY;
    }
}
