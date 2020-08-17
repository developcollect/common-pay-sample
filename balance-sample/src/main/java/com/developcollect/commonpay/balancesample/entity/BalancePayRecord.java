package com.developcollect.commonpay.balancesample.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 余额支付记录
 *
 * @author Zhu Kaixiao
 * @version 1.0
 */
@Data
public class BalancePayRecord implements Serializable {

    private Long id;

    private Long amount;

}
