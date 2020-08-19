package com.developcollect.commonpay.customizeconfigsample.config;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.developcollect.commonpay.notice.IPayBroadcaster;
import com.developcollect.commonpay.notice.IRefundBroadcaster;
import com.developcollect.commonpay.pay.IOrder;
import com.developcollect.commonpay.pay.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 支付组件配置
 * 示范如何覆盖common-pay已有的组件, 和添加原先没有的组件, 以下示范的组件在实际使用中按需求覆盖即可, 并没有限制一定要全部覆盖
 *
 *
 * 具体有哪些组件可以替换和添加, 可以查看
 * {@link com.developcollect.commonpay.autoconfig.CommonPayAutoConfig.DefaultCommonPayConfigurer}中有注入哪些组件
 *
 * 注意: 组件注入是有限制bean的名称的, 具体的名称可查看
 * {@link com.developcollect.commonpay.autoconfig.CommonPayAutoConfig.DefaultCommonPayConfigurer}, 后续会在文档中详细标出
 *
 * <p>
 * 这里只示范组件替换, 如果需要自己实现配置器, 完全接管配置, 见customize-config-sample2
 *
 * @author zak
 * @version 1.0
 * @date 2020/8/7 16:44
 */
@Slf4j
@Configuration
public class CommonPayConfig {


    /**
     * 支付宝支付结果异步通知地址生成器
     */
    @Bean
    Function<IOrder, String> aliPayPayNotifyUrlGenerator() {
        // 这里要确保域名能正确解析到本机, 否则无法接受通知结果, 这个地址默认是common-pay自动生成的接口, 也可以改成自己的
        return o -> String.format("%s/cPay/alipay", "http://www.baidu.com");
    }

    /**
     * 支付宝PC页面支付完成后跳转地址
     * 注意: 这是返回的页面跳转地址, 并不会覆盖结果通知地址
     */
    @Bean
    Function<IOrder, String> aliPayPcReturnUrlGenerator() {
        return o -> String.format("%s/a.html?o=%s&a=%s", "http://www.baidu.com", o.getOutTradeNo(), o.getTotalFee());
    }

    /**
     * 支付宝WAP页面支付完成后跳转地址
     * 注意: 这是返回的页面跳转地址, 并不会覆盖结果通知地址
     */
    @Bean
    Function<IOrder, String> aliPayWapReturnUrlGenerator() {
        return o -> String.format("%s/a.html?o=%s&a=%s", "http://www.baidu.com", o.getOutTradeNo(), o.getTotalFee());
    }

    /**
     * 支付宝支付二维码访问地址生成器
     */
    @Bean
    BiFunction<IOrder, String, String> aliPayPayQrCodeAccessUrlGenerator() {
        return (order, content) -> {
            byte[] bytes = QrCodeUtil.generatePng(content, 300, 300);
            // 生成二维码后可以上传到ftp,或者oss, 然后返回访问链接就可以
            // 默认是生成文件在本地, 然后通过静态资源映射访问
            // 注: 二维码支付推荐使用PayUtil.payQrCode, PayUtil.payQrCodeBase64方法,
            //     因为不需要访问路径, 所以不会生成本地文件
            return "http://dwz.date/caVF";
        };
    }

    /**
     * 支付宝PC支付页面访问地址生成器
     */
    @Bean
    BiFunction<IOrder, String, String> aliPayPcPayFormHtmlAccessUrlGenerator() {
        return (order, content) -> {
//            AliPayConfig payConfig = GlobalConfig.getPayConfig(PayPlatform.ALI_PAY);
//            String appHome = SpringUtil.appHome();
//            FileUtil.writeString(content, appHome + "/cc/aa.html", payConfig.getCharset());
//            同上, 生成的html文件可以上传到ftp或oss, 只需要返回访问地址就可以
            return "http://dwz.date/caVF";
        };
    }

    /**
     * 支付宝WAP支付页面访问地址生成器
     */
    @Bean
    BiFunction<IOrder, String, String> aliPayWapPayFormHtmlAccessUrlGenerator() {
        // 同上
        return (order, content) -> "http://dwz.date/caVF";
    }

    /**
     * 临时文件清理器
     * 因为像二维码访问地址方法需要生成文件, 在支付完成后就需要把文件清理掉
     */
    @Bean
    Consumer<PayResponse> aliPayTempFileClear() {
        return payResponse -> {
            String outTradeNo = payResponse.getOutTradeNo();
            log.info("根据订单号[{}]删除文件", outTradeNo);
        };
    }

    /*
     * 微信支付同理
     */

    /**
     * 支付结果广播器
     * 默认的广播器是基于spring的事件通知实现的
     * 通过覆盖广播器, 可以实现MQ广播, RPC通知等等
     * MQ广播可以参考stream-sample
     */
    @Bean
    IPayBroadcaster payBroadcaster() {
        return payResponse -> {
            log.info("支付结果广播: [{}]", payResponse);
            return true;
        };
    }

    /**
     * 退款结果广播器
     * 同上
     */
    @Bean
    IRefundBroadcaster refundBroadcaster() {
        return refundResponse -> {
            log.info("退款结果广播: [{}]", refundResponse);
            return true;
        };
    }


}
