package com.developcollect.commonpay.streamsample.config.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2020/7/29 9:08
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
public interface PaySource {


    String PAY_OUTPUT = "payOutput";

    @Output(PAY_OUTPUT)
    MessageChannel payOutput();


    String PAY_INPUT = "payInput";

    @Input(PAY_INPUT)
    SubscribableChannel payInput();

}
