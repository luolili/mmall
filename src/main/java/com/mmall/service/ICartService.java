package com.mmall.service;

import com.mmall.common.ServerResponse;

public interface ICartService {

    ServerResponse add(Integer userId, Integer count, Integer productId);

    ServerResponse update(Integer userId, Integer count, Integer productId);

    ServerResponse deleteProduct(Integer userId, String productIds);

    ServerResponse list(Integer userId);


    ServerResponse selectOrUnselect(Integer userId, Integer checked, Integer productId);
}
