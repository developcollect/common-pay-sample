package com.developcollect.commonpay.streamsample.config.mq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2020/8/13 9:24
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@EnableBinding({PaySource.class})
@Configuration
public class MqConfig {
}
