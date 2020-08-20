package com.developcollect.commonpay.streamsample.entity;

import lombok.Data;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/10 14:29

 */
@Data
public class LocalRefund {

    private String code;

    private String refundNo;

    private Long refundAmount;

    private Integer payType;
}
