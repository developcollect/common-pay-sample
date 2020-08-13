package com.developcollect.commonpay.simplesample.service;


import com.developcollect.commonpay.simplesample.entity.LocalOrder;

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
