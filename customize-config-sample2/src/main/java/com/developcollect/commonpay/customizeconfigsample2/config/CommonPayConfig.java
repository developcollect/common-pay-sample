package com.developcollect.commonpay.customizeconfigsample2.config;

import com.developcollect.commonpay.PayPlatform;
import com.developcollect.commonpay.autoconfig.CommonPayConfigurer;
import com.developcollect.commonpay.config.*;
import com.developcollect.commonpay.pay.alipay.Alipay;
import com.developcollect.commonpay.pay.nopay.NoPay;
import com.developcollect.commonpay.pay.wxpay.WxPay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Supplier;

/**
 * 支付配置
 * 示范如何实现自定义配置器, 达到完全接管common-pay配置
 * <p>
 * 这里只示范通过实现配置器完全接管配置, 如果只是需要覆盖一部分组件 见customize-config-sample
 *
 * @author zak
 * @version 1.0
 * @date 2020/8/7 16:44
 */
@Slf4j
@Configuration
public class CommonPayConfig implements CommonPayConfigurer {


    /**
     * 这里只是示范如何配置, 代码并不是最优写法
     */
    @Override
    public void config(GlobalConfig globalConfig) {
        // 支付平台 与 支付配置提供器 map
        // 后续的支付过程会根据支付平台到这个map中取相应的支付配置提供器，从而拿到相应的支付配置
        Map<Integer, Supplier<? extends AbstractPayConfig>> payConfigSupplierMap = globalConfig.getPayConfigSupplierMap();

        // 支付宝配置
        Supplier<AliPayConfig> aliPayConfigSupplier = () -> {
            AliPayConfig aliPayConfig = new AliPayConfig();
            // 具体的值可以来自任何地方, 比如数据库,网络等等..
            aliPayConfig.setAppId("aaa");
            aliPayConfig.setPrivateKey("aaa");
            aliPayConfig.setPublicKey("aaa");
            aliPayConfig.setCharset("UTF-8");
            aliPayConfig.setSignType("RSA2");
            aliPayConfig.setDebug(false);
            aliPayConfig.setRefundNotifyUrlGenerator((o, r) -> "http://www.baidu.com/xxx");
            aliPayConfig.setPayNotifyUrlGenerator(o -> "http://www.baidu.com/xxx");
            aliPayConfig.setPayQrCodeAccessUrlGenerator((o, c) -> "http://www.baidu.com/xxx");
            aliPayConfig.setPcPayFormHtmlAccessUrlGenerator((o, c) -> "http://www.baidu.com/xxx");
            aliPayConfig.setPcReturnUrlGenerator(o -> "http://www.baidu.com/xxx");
            aliPayConfig.setWapPayFormHtmlAccessUrlGenerator((o, c) -> "http://www.baidu.com/xxx");
            aliPayConfig.setWapReturnUrlGenerator(o -> "http://www.baidu.com/xxx");
            aliPayConfig.setQrCodeWidth(300);
            aliPayConfig.setQrCodeHeight(300);
            aliPayConfig.putExt("扩展配置", "扩展配置的值");
            aliPayConfig.putExt("扩展配置2", "扩展配置2的值");
            return aliPayConfig;
        };
        // 设置支付宝配置提供器
        payConfigSupplierMap.put(PayPlatform.ALI_PAY, aliPayConfigSupplier);


        Supplier<WxPayConfig> wxPayConfigSupplier = () -> {
            WxPayConfig wxPayConfig = new WxPayConfig();
            wxPayConfig.setAppId("aaa");
            wxPayConfig.setMchId("aaa");
            wxPayConfig.setKey("aaa");
            wxPayConfig.setCertInputStreamSupplier(() -> null);
            wxPayConfig.setDebug(false);
            wxPayConfig.setRefundNotifyUrlGenerator((o, r) -> "http://www.baidu.com/xxx");
            wxPayConfig.setPayNotifyUrlGenerator(o -> "http://www.baidu.com/xxx");
            wxPayConfig.setPayQrCodeAccessUrlGenerator((o, c) -> "http://www.baidu.com/xxx");
            // 因为微信支付不支持pc页面支付, 微信wap支付是直接返回的链接, 不需要生成, 所以下面三个都不需要配置
//            wxPayConfig.setPcPayFormHtmlAccessUrlGenerator((o, c) -> "http://www.baidu.com/xxx");
//            wxPayConfig.setPcReturnUrlGenerator(o -> "http://www.baidu.com/xxx");
//            wxPayConfig.setWapPayFormHtmlAccessUrlGenerator((o, c) -> "http://www.baidu.com/xxx");
            wxPayConfig.setWapReturnUrlGenerator(o -> "http://www.baidu.com/xxx");
            wxPayConfig.setQrCodeWidth(300);
            wxPayConfig.setQrCodeHeight(300);
            wxPayConfig.putExt("扩展配置", "扩展配置的值");
            wxPayConfig.putExt("扩展配置2", "扩展配置2的值");
            return wxPayConfig;
        };
        // 设置微信支付配置提供器
        payConfigSupplierMap.put(PayPlatform.WX_PAY, wxPayConfigSupplier);



        /* -----------------------通过自定义广播器即可实现MQ通知, RPC通知等等---------------------- */
        // 设置支付结果广播器（必须）
        globalConfig.setPayBroadcaster(payResponse -> {
            log.info("支付结果广播器: {}", payResponse);
            return true;
        });

        // 设置退款结果广播器
        globalConfig.setRefundBroadcaster(refundResponse -> {
            log.info("退款结果广播器: {}", refundResponse);
            return true;
        });

        // 设置Pay工厂， 因为common-pay中有默认的工厂， 所以只有在自定义pay的情况下才覆盖默认工厂
        // 自定义支付参考 balance-sample
        IPayFactory payFactory = payPlatform -> {
            if (payPlatform == PayPlatform.ALI_PAY) {
                return new Alipay();
            } else if (payPlatform == PayPlatform.WX_PAY) {
                return new WxPay();
            } else {
                return new NoPay();
            }
        };
        globalConfig.setPayFactory(payFactory);

        // 设置主动查询通知间隔时间， 用于控制主动查询支付状态的频率
        globalConfig.setQueryNoticeDelay(600000);
    }


}
