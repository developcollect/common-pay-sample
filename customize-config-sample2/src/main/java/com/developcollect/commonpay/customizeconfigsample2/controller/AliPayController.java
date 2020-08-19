package com.developcollect.commonpay.customizeconfigsample2.controller;


import com.developcollect.commonpay.PayPlatform;
import com.developcollect.commonpay.PayUtil;
import com.developcollect.commonpay.customizeconfigsample2.entity.LocalOrder;
import com.developcollect.commonpay.customizeconfigsample2.entity.LocalRefund;
import com.developcollect.commonpay.customizeconfigsample2.service.IOrderService;
import com.developcollect.commonpay.customizeconfigsample2.service.IRefundService;
import com.developcollect.commonpay.customizeconfigsample2.service.impl.OrderAdapter;
import com.developcollect.commonpay.customizeconfigsample2.service.impl.RefundAdapter;
import com.developcollect.commonpay.pay.PayResponse;
import com.developcollect.commonpay.pay.RefundResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/7 16:28
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/alipay")
public class AliPayController {

    private final IOrderService orderService;
    private final IRefundService refundService;

    @GetMapping("/refund")
    public RefundResponse refund(
            @RequestParam String orderCode,
            @RequestParam long amount
    ) {
        LocalOrder localOrder = orderService.getOrder(orderCode);
        LocalRefund localRefund = refundService.createRefund(orderCode, amount);
        OrderAdapter orderAdapter = OrderAdapter.of(localOrder);
        RefundAdapter refundAdapter = RefundAdapter.of(localRefund);

        RefundResponse refundResponse = PayUtil.refundSync(orderAdapter, refundAdapter);

        return refundResponse;
    }

    @GetMapping("/alipay1")
    public Map alipay() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        String payForm = PayUtil.payPcForm(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/alipay2")
    public Map alipay2() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        String payForm = PayUtil.payPcFormAccessUrl(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/alipay3")
    public Map alipay3() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        String payForm = PayUtil.payQrCodeAccessUrl(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/alipay4")
    public Map alipay4() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        String payForm = PayUtil.payQrCode(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/alipay5")
    public Map alipay5() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        String payForm = PayUtil.payQrCodeBase64(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/alipay6")
    public Map alipay6() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        PayResponse payResponse = PayUtil.paySync(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payResponse);
        return map;
    }

    @GetMapping("/alipay7")
    public Map alipay7() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        String payForm = PayUtil.payWapForm(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/alipay8")
    public Map alipay8() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        String payForm = PayUtil.payWapFormAccessUrl(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }
}
