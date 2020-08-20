package com.developcollect.commonpay.streamsample.service.impl;

import com.developcollect.commonpay.pay.IPayDTO;
import com.developcollect.commonpay.streamsample.entity.LocalOrder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/7 16:30
 */
@RequiredArgsConstructor
public class PayDTO implements IPayDTO<LocalOrder> {

    private final LocalOrder localOrder;


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


    public static PayDTO of(LocalOrder localOrder) {
        return new PayDTO(localOrder);
    }

}
