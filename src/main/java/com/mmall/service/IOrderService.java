package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.OrderVO;

public interface IOrderService {
    ServerResponse createOrder(Integer userId, Integer shippingId);

    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse<String> cancel(Integer userId, Long orderNo);

    //
    ServerResponse getCartProduct(Integer userId);

    //获取订单详情
    ServerResponse getOrderDetail(Integer userId, Long orderNo);

    ServerResponse getOrderList(Integer userId, Integer pageNum, Integer pageSize);

    //backend
    ServerResponse getOrderList(Integer pageNum, Integer pageSize);

    ServerResponse<OrderVO> manageDetail(Long orderNo);

    ServerResponse manageSearch(Long orderNo, int pageNum, int pageSize);
}
