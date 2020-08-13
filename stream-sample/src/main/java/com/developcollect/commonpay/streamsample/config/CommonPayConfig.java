package com.developcollect.commonpay.streamsample.config;


import com.developcollect.commonpay.autoconfig.CommonPayProperties;
import com.developcollect.commonpay.notice.IPayBroadcaster;
import com.developcollect.commonpay.pay.IOrder;
import com.developcollect.commonpay.streamsample.config.mq.PaySource;
import com.developcollect.commonpay.utils.SerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Function;


/**
 * @author zak
 * @version 1.0
 * @date 2020/8/7 16:44
 */
@Slf4j
@Configuration
public class CommonPayConfig {

    @Autowired
    private PaySource payOutput;

    @Autowired
    private CommonPayProperties commonPayProperties;


    @Bean
    public Function<IOrder, String> aliPayReturnUrlGenerator() {
        return o -> String.format("%s/a.html?o=%s&a=%s", commonPayProperties.getUrlPrefix(), o.getOutTradeNo(), o.getTotalFee());
    }

    @Bean
    IPayBroadcaster payBroadcaster() {
        return payResponse -> {
            Message<byte[]> message = MessageBuilder.withPayload(SerializeUtil.jdkSerialize(payResponse)).build();
            boolean send = payOutput.payOutput().send(message);
            if (send == false) {
                log.error("支付消息推送失败");
            }
            return true;
        };
    }

}
