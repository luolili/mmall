package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import com.mmall.pojo.Product;

import java.util.List;

public interface IProductService {

    ServerResponse saveOrUpdate(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);


    ServerResponse getDetail(Integer productId);
}
