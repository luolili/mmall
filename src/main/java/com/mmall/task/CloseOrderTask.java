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

    //@PreDestroy
    private void delLock() {
        RedisShardedPoolUtil.del(Const.RedisLock.CLOSE_ORDER_TASK_LOCK);
    }
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
        } else {
            //设置失败
            log.info("设置失败");
        }
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void closeOrderV3() {
        long lockTimeout = Long.parseLong(PropertyUtil.getProperty("lock.timeout", "60000"));
        //orderService.closeOrder(hour);
        Long result = RedisShardedPoolUtil.setnx(Const.RedisLock.CLOSE_ORDER_TASK_LOCK,
                String.valueOf(System.currentTimeMillis() + lockTimeout));
        if (result != null && result.intValue() == 1) {
            closeOrder(Const.RedisLock.CLOSE_ORDER_TASK_LOCK);
        } else {
            //设置失败
            log.info("设置失败");
            //没有获取到锁，但锁存在
            String lockValueStr = RedisShardedPoolUtil.get(Const.RedisLock.CLOSE_ORDER_TASK_LOCK);
            if (lockValueStr != null && System.currentTimeMillis() > Long.parseLong(lockValueStr)) {
                String getSetValue = RedisShardedPoolUtil.getSet(Const.RedisLock.CLOSE_ORDER_TASK_LOCK,
                        String.valueOf(System.currentTimeMillis() + lockTimeout));//reset value,get old value
                if (getSetValue == null || getSetValue.equals(lockValueStr)) {
                    //get the lock
                    closeOrder(Const.RedisLock.CLOSE_ORDER_TASK_LOCK);
                } else {
                    log.info("not get lock");
                }
            } else {
                log.info("not get lock");
            }
        }
    }

    private void closeOrder(String lockName) {
        RedisShardedPoolUtil.expire(lockName, 50);
        log.info("thread:{}", Thread.currentThread().getName());
        int hour = Integer.parseInt(PropertyUtil.getProperty("close.order.task.time.order", "2"));
        // orderService.closeOrder(hour);
        log.info("关闭订单结束");
        RedisShardedPoolUtil.del(Const.RedisLock.CLOSE_ORDER_TASK_LOCK);// 释放分布式锁
    }


}
