package com.developcollect.commonpay.customizeconfigsample.controller;

import com.developcollect.commonpay.PayPlatform;
import com.developcollect.commonpay.PayUtil;
import com.developcollect.commonpay.customizeconfigsample.entity.LocalOrder;
import com.developcollect.commonpay.customizeconfigsample.entity.LocalRefund;
import com.developcollect.commonpay.customizeconfigsample.service.IOrderService;
import com.developcollect.commonpay.customizeconfigsample.service.IRefundService;
import com.developcollect.commonpay.customizeconfigsample.service.impl.OrderAdapter;
import com.developcollect.commonpay.customizeconfigsample.service.impl.RefundAdapter;
import com.developcollect.commonpay.pay.PayWxJsResult;
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
@RequestMapping("/wxpay")
public class WxPayController2 {

    private final IOrderService orderService;
    private final IRefundService refundService;

    private String openId = "owKPNwzDznbnyLnEQwJH35KOIRrg";


    @GetMapping("/wxpay1")
    public Map wxpay3() {
        LocalOrder order = orderService.getOrderSpecifyAmount(1L);
        order.setPayType(PayPlatform.WX_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        String payForm = PayUtil.payQrCodeAccessUrl(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/wxpay2")
    public Map wxpay4() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.WX_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        String payForm = PayUtil.payQrCode(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/wxpay3")
    public Map wxpay5() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.WX_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        String payForm = PayUtil.payQrCodeBase64(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/wxpay6")
    public Map wxpay6() {
        LocalOrder order = orderService.getOrderSpecifyAmount(1);
        order.setPayType(PayPlatform.WX_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        PayWxJsResult payWxJsResult = PayUtil.payWxJs(orderAdapter, openId);

        Map map = new HashMap();
        map.put("payForm", payWxJsResult);
        return map;
    }

    @GetMapping("/wxpay7")
    public Map wxpay7() {
        LocalOrder order = orderService.getOrderSpecifyAmount(1);
        order.setPayType(PayPlatform.WX_PAY);
        OrderAdapter orderAdapter = new OrderAdapter(order);
        String s = PayUtil.payWapFormAccessUrl(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", s);
        return map;
    }


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

}
