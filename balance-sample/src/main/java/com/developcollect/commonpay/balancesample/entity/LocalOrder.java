package com.developcollect.commonpay.balancesample.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/7 16:30
 */
@Data
public class LocalOrder {


    private String code;

    private String serialNumber;

    private Long totalFee;

    private Integer payType;

    private LocalDateTime timeStart;

    private LocalDateTime timeExpire;

}
