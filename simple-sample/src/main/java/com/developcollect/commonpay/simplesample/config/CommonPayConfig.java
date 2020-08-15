package com.developcollect.commonpay.simplesample.config;


import com.developcollect.commonpay.autoconfig.CommonPayProperties;
import com.developcollect.commonpay.pay.IOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/7 16:44
 */
@Configuration
public class CommonPayConfig {

    @Autowired
    private CommonPayProperties commonPayProperties;


    @Bean
    public Function<IOrder, String> aliPayPcReturnUrlGenerator() {
        return o -> String.format("%s/a.html?o=%s&a=%s", commonPayProperties.getUrlPrefix(), o.getOutTradeNo(), o.getTotalFee());
    }


}
