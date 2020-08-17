package com.developcollect.commonpay.balancesample.service;


import com.developcollect.commonpay.balancesample.entity.LocalRefund;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2020/8/10 14:28
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
public interface IRefundService {

    LocalRefund createRefund(String orderCode, long amount);
}
