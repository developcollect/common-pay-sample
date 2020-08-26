package com.developcollect.commonpay.balancesample.config.commonpay;

import com.developcollect.commonpay.balancesample.entity.LocalOrder;
import com.developcollect.commonpay.pay.IPayDTO;

import java.time.LocalDateTime;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/7 16:30
 */
public class OrderAdapter implements IPayDTO<LocalOrder> {

    private LocalOrder localOrder;


    @Override
    public Long getTotalFee() {
        return localOrder.getTotalFee();
    }

    @Override
    public LocalDateTime getTimeStart() {
        return localOrder.getTimeStart();
    }

    @Override
    public LocalDateTime getTimeExpire() {
        return localOrder.getTimeExpire();
    }

    @Override
    public LocalOrder getSource() {
        return localOrder;
    }

    @Override
    public String getOutTradeNo() {
        return localOrder.getCode();
    }

    @Override
    public String getTradeNo() {
        return localOrder.getSerialNumber();
    }

    @Override
    public int getPayPlatform() {
        return localOrder.getPayType();
    }


    public static OrderAdapter of(LocalOrder localOrder) {
        OrderAdapter orderAdapter = new OrderAdapter();
        orderAdapter.localOrder = localOrder;
        return orderAdapter;
    }

}
