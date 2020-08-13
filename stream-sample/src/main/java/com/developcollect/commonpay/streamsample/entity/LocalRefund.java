package com.developcollect.commonpay.streamsample.entity;

import lombok.Data;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2020/8/10 14:29
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Data
public class LocalRefund {

    private String code;

    private Long refundAmount;

    private Integer payType;
}
