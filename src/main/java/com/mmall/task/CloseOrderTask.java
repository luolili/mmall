package com.mmall.task;

import com.mmall.service.IOrderService;
import com.mmall.util.PropertyUtil;
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

    @Scheduled(cron = "0 */1 * * * ?")
    public void closeOrderV1() {
        int hour = Integer.parseInt(PropertyUtil.getProperty("close.order.task.time.order", "2"));
        orderService.closeOrder(hour);
        log.info("关闭订单结束");
    }


}
