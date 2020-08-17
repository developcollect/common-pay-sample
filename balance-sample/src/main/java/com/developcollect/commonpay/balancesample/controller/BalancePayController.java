package com.developcollect.commonpay.balancesample.controller;

import com.developcollect.commonpay.PayUtil;
import com.developcollect.commonpay.balancesample.config.commonpay.LocalPayPlatform;
import com.developcollect.commonpay.balancesample.config.commonpay.OrderAdapter;
import com.developcollect.commonpay.balancesample.entity.LocalOrder;
import com.developcollect.commonpay.balancesample.service.IOrderService;
import com.developcollect.commonpay.pay.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2020/8/17 9:26
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@RestController
@RequestMapping("/balance")
public class BalancePayController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/balancePay")
    public PayResponse balancePay() {
        LocalOrder order = orderService.getOrder();
        OrderAdapter orderAdapter = new OrderAdapter(order);

        PayResponse payResponse = PayUtil.paySync(LocalPayPlatform.BALANCE_PAY, orderAdapter);

        return payResponse;
    }
}
