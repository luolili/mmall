package com.mmall.task;

import com.mmall.common.Const;
import com.mmall.service.IOrderService;
import com.mmall.util.PropertyUtil;
import com.mmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 关单
 */
@Component
@Slf4j
public class CloseOrderTask {
    @Autowired
    private IOrderService orderService;

    //@Scheduled(cron = "0 */1 * * * ?")
    public void closeOrderV1() {
        int hour = Integer.parseInt(PropertyUtil.getProperty("close.order.task.time.order", "2"));
        orderService.closeOrder(hour);
        log.info("关闭订单结束");
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void closeOrderV2() {
        long lockTimeout = Long.parseLong(PropertyUtil.getProperty("lock.timeout", "60000"));
        //orderService.closeOrder(hour);
        Long result = RedisShardedPoolUtil.setnx(Const.RedisLock.CLOSE_ORDER_TASK_LOCK,
                String.valueOf(System.currentTimeMillis() + lockTimeout));
        if (result != null && result.intValue() == 1) {
            closeOrder(Const.RedisLock.CLOSE_ORDER_TASK_LOCK);
            log.info("关闭订单结束");
        } else {
            //设置失败
            log.info("设置失败");
        }
    }

    private void closeOrder(String lockName) {
        RedisShardedPoolUtil.expire(lockName, 50);
        log.info("thread:{}", Thread.currentThread().getName());
        int hour = Integer.parseInt(PropertyUtil.getProperty("close.order.task.time.order", "2"));
        orderService.closeOrder(hour);
        log.info("关闭订单结束");
        RedisShardedPoolUtil.del(Const.RedisLock.CLOSE_ORDER_TASK_LOCK);// 释放分布式锁
    }
}
