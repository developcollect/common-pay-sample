package com.developcollect.commonpay.balancesample.service;

import com.developcollect.commonpay.balancesample.entity.BalancePayRecord;
import com.developcollect.commonpay.balancesample.entity.LocalOrder;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/17 9:27
 */
public interface IBalanceService {


    BalancePayRecord pay(LocalOrder localOrder);

}
