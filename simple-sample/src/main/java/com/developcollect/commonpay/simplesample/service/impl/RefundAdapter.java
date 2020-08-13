package com.developcollect.commonpay.simplesample.service.impl;

import com.developcollect.commonpay.pay.IRefund;
import com.developcollect.commonpay.simplesample.entity.LocalRefund;
import lombok.RequiredArgsConstructor;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2020/8/10 14:33
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
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
