package com.mmall.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.mmall.common.ServerResponse;
import com.mmall.dao.OrderMapper;
import com.mmall.pojo.Order;
import com.mmall.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    public ServerResponse pay(Long orderNo, Integer userId, String path) {
        Map<String, String> resultMap = Maps.newHashMap();
        Order order = orderMapper.selectByUserIdOrderNo(userId, orderNo);

        if (order == null) {
            return ServerResponse.createByErrorMessage("该用户没有该订单");
        }
        resultMap.put("orderNo", String.valueOf(order.getOrderNo()));

        //-1 订单系统的唯一订单号, 64个字符以内
        String outTradeNo = order.getOrderNo().toString();
        // -2 订单 标题
        String subject = new StringBuilder()
                .append("mmall ,订单号：")
                .append(outTradeNo).toString();
        // -3 订单总价
        String totalAmount = order.getPayment().toString();
        //-4 不可打折的金额
        String undiscountableAmount = "0";

        //-5 商家的id: 卖家支付宝id， 若他为空，默认为支付宝的商家的pid
        String sellerId = "";
        //-6 订单描述
        String body = new StringBuilder()
                .append(outTradeNo + " 总价：")
                .append(totalAmount).toString();
        //-7 商家操作员id
        String operatorId = "test_operator_id";
        String storeId = "test_store_id";//门店id
        //-8 支付timeout时间
        String timeoutExpress = "120m";


        return null;
    }
}
