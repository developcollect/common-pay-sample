package com.developcollect.commonpay.balancesample.config.commonpay;

import com.developcollect.commonpay.balancesample.entity.LocalRefund;
import com.developcollect.commonpay.pay.IRefundDTO;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/10 14:33
 */
public class RefundAdapter implements IRefundDTO<LocalRefund> {

    private LocalRefund localRefund;

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
        RefundAdapter refundAdapter = new RefundAdapter();
        refundAdapter.localRefund = localRefund;
        return refundAdapter;
    }
}
