package com.developcollect.commonpay.simplesample.controller;

import com.developcollect.commonpay.PayPlatform;
import com.developcollect.commonpay.PayUtil;
import com.developcollect.commonpay.pay.PayWxJsResult;
import com.developcollect.commonpay.pay.RefundResponse;
import com.developcollect.commonpay.simplesample.entity.LocalOrder;
import com.developcollect.commonpay.simplesample.entity.LocalRefund;
import com.developcollect.commonpay.simplesample.service.IOrderService;
import com.developcollect.commonpay.simplesample.service.IRefundService;
import com.developcollect.commonpay.simplesample.service.impl.PayDTO;
import com.developcollect.commonpay.simplesample.service.impl.RefundAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 支持微信沙箱环境
 * 使用沙箱环境前需要先获取沙箱环境的key，见：https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=23_1&index=1
 * 之后在配置文件中配置沙箱key，而不是正式环境key
 * 最后就可以使用微信沙箱环境了。
 * 需要注意的是微信的沙箱环境不能随意指定订单金额，需要用固定的金额，每种金额代表不同的场景，具体详情见微信的验收用例：
 * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=23_12
 *
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
        LocalOrder order = orderService.getOrderSpecifyAmount(301);
        order.setPayType(PayPlatform.WX_PAY);
        PayDTO orderAdapter = new PayDTO(order);
        String payForm = PayUtil.payQrCodeAccessUrl(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/wxpay2")
    public Map wxpay4() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.WX_PAY);
        PayDTO orderAdapter = new PayDTO(order);
        String payForm = PayUtil.payQrCode(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/wxpay3")
    public Map wxpay5() {
        LocalOrder order = orderService.getOrder();
        order.setPayType(PayPlatform.WX_PAY);
        PayDTO orderAdapter = new PayDTO(order);
        String payForm = PayUtil.payQrCodeBase64(orderAdapter);

        Map map = new HashMap();
        map.put("payForm", payForm);
        return map;
    }

    @GetMapping("/wxpay6")
    public Map wxpay6() {
        LocalOrder order = orderService.getOrderSpecifyAmount(1);
        order.setPayType(PayPlatform.WX_PAY);
        PayDTO orderAdapter = new PayDTO(order);
        PayWxJsResult payWxJsResult = PayUtil.payWxJs(orderAdapter, openId);

        Map map = new HashMap();
        map.put("payForm", payWxJsResult);
        return map;
    }

    @GetMapping("/wxpay7")
    public Map wxpay7() {
        LocalOrder order = orderService.getOrderSpecifyAmount(1);
        order.setPayType(PayPlatform.WX_PAY);
        PayDTO orderAdapter = new PayDTO(order);
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
        PayDTO orderAdapter = PayDTO.of(localOrder);
        RefundAdapter refundAdapter = RefundAdapter.of(localRefund);

        RefundResponse refundResponse = PayUtil.refundSync(orderAdapter, refundAdapter);

        return refundResponse;
    }

}
