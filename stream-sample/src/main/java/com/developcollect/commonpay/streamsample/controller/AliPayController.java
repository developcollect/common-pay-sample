package com.developcollect.commonpay.streamsample.controller;


import com.developcollect.commonpay.PayPlatform;
import com.developcollect.commonpay.PayUtil;
import com.developcollect.commonpay.pay.PayResponse;
import com.developcollect.commonpay.pay.RefundResponse;
import com.developcollect.commonpay.streamsample.entity.LocalOrder;
import com.developcollect.commonpay.streamsample.entity.LocalRefund;
import com.developcollect.commonpay.streamsample.service.IOrderService;
import com.developcollect.commonpay.streamsample.service.IRefundService;
import com.developcollect.commonpay.streamsample.service.impl.PayDTO;
import com.developcollect.commonpay.streamsample.service.impl.RefundAdapter;
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
        PayDTO orderAdapter = PayDTO.of(localOrder);
        RefundAdapter refundAdapter = RefundAdapter.of(localRefund);

        RefundResponse refundResponse = PayUtil.refundSync(orderAdapter, refundAdapter);

        return refundResponse;
    }

    @GetMapping("/alipay1")
    public Map alipay() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        PayDTO orderAdapter = new PayDTO(order);
        String payForm = PayUtil.payPcForm(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/alipay2")
    public Map alipay2() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        PayDTO orderAdapter = new PayDTO(order);
        String payForm = PayUtil.payPcFormAccessUrl(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/alipay3")
    public Map alipay3() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        PayDTO orderAdapter = new PayDTO(order);
        String payForm = PayUtil.payQrCodeAccessUrl(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/alipay4")
    public Map alipay4() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        PayDTO orderAdapter = new PayDTO(order);
        String payForm = PayUtil.payQrCode(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/alipay5")
    public Map alipay5() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        PayDTO orderAdapter = new PayDTO(order);
        String payForm = PayUtil.payQrCodeBase64(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/alipay6")
    public Map alipay6() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.ALI_PAY);
        PayDTO orderAdapter = new PayDTO(order);
        PayResponse payResponse = PayUtil.paySync(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payResponse);
        return map;
    }
}
