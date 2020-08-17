package com.developcollect.commonpay.balancesample.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import com.developcollect.commonpay.balancesample.entity.BalancePayRecord;
import com.developcollect.commonpay.balancesample.entity.LocalOrder;
import com.developcollect.commonpay.balancesample.service.IBalanceService;
import com.developcollect.commonpay.utils.UnitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zak
 * @version 1.0
 * @date 2020/8/17 9:28
 */
@Slf4j
@Service
public class BalanceServiceImpl implements IBalanceService {


    /**
     * 模拟支付过程
     */
    @Override
    public BalancePayRecord pay(LocalOrder localOrder) {

        log.info("订单[{}]支付中, 扣减余额:[{}]", localOrder.getCode(), UnitUtil.convertFenToYuanStr(localOrder.getTotalFee()));
        ThreadUtil.sleep(3000);

        log.info("订单[{}]支付成功", localOrder.getCode());
        ThreadUtil.sleep(2000);

        BalancePayRecord payRecord = new BalancePayRecord();
        payRecord.setId(RandomUtil.randomLong(5000000000L, 9999999999L));
        payRecord.setAmount(localOrder.getTotalFee());
        log.info("生成支付记录: [{}]", payRecord);
        return payRecord;
    }
}
