package com.mmall.service;

import com.mmall.common.ServerResponse;

public interface IOrderService {
    ServerResponse createOrder(Integer userId, Integer shippingId);

    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse<String> cancel(Integer userId, Long orderNo);

    //
    ServerResponse getCartProduct(Integer userId);

    //获取订单详情
    ServerResponse getOrderDetail(Integer userId, Long orderNo);

    ServerResponse getOrderList(Integer userId, Integer pageNum, Integer pageSize);
}
