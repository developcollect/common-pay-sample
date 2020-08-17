package com.developcollect.commonpay.simplesample.service;

import com.developcollect.commonpay.simplesample.entity.LocalRefund;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/10 14:28

 */
public interface IRefundService {

    LocalRefund createRefund(String orderCode, long amount);
}
