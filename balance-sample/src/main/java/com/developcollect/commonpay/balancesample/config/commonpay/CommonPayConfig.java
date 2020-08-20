package com.developcollect.commonpay.balancesample.config.commonpay;


import com.developcollect.commonpay.autoconfig.CommonPayProperties;
import com.developcollect.commonpay.balancesample.service.IBalanceService;
import com.developcollect.commonpay.pay.IPayDTO;
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
    Function<IPayDTO, String> aliPayPcReturnUrlGenerator() {
        return o -> String.format("%s/a.html?o=%s&a=%s", commonPayProperties.getUrlPrefix(), o.getOutTradeNo(), o.getTotalFee());
    }

    @Bean
    LocalPayFactory payFactory(IBalanceService balanceService) {
        return new LocalPayFactory(balanceService);
    }


}
