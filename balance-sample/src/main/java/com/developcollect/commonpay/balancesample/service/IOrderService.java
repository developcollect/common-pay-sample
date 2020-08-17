package com.developcollect.commonpay.balancesample.service;


import com.developcollect.commonpay.balancesample.entity.LocalOrder;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/7 16:27
 */
public interface IOrderService {

    LocalOrder getOrderSpecifyAmount(long amount);

    LocalOrder getOrder();

    LocalOrder getOrder(String code);
}
