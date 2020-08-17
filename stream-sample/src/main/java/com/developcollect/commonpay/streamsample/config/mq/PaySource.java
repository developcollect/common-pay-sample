package com.developcollect.commonpay.streamsample.config.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zak
 * @version 1.0
 * @date 2020/7/29 9:08

 */
public interface PaySource {


    String PAY_OUTPUT = "payOutput";

    @Output(PAY_OUTPUT)
    MessageChannel payOutput();


    String PAY_INPUT = "payInput";

    @Input(PAY_INPUT)
    SubscribableChannel payInput();

}
