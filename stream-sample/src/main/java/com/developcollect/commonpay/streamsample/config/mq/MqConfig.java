package com.developcollect.commonpay.streamsample.config.mq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/13 9:24

 */
@EnableBinding({PaySource.class})
@Configuration
public class MqConfig {
}
