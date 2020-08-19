package com.developcollect.commonpay.customizeconfigsample.service.impl;

import com.developcollect.commonpay.customizeconfigsample.entity.LocalRefund;
import com.developcollect.commonpay.pay.IRefund;
import lombok.RequiredArgsConstructor;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/10 14:33
 */
@RequiredArgsConstructor
public class RefundAdapter implements IRefund<LocalRefund> {

    private final LocalRefund localRefund;

    @Override
    public String getOutRefundNo() {
        return localRefund.getCode();
    }

    @Override
    public Long getRefundFee() {
        return localRefund.getRefundAmount();
    }

    @Override
    public int getPayPlatform() {
        return localRefund.getPayType();
    }

    @Override
    public LocalRefund getSource() {
        return localRefund;
    }

    public static RefundAdapter of(LocalRefund localRefund) {
        return new RefundAdapter(localRefund);
    }
}
