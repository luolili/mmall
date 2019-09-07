package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;

public interface IShippingService {
    ServerResponse add(Integer id, Shipping shipping);


    ServerResponse deleteShipping(Integer id, Integer shippingId);


}
