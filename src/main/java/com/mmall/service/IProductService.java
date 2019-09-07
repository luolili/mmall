package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import com.mmall.pojo.Product;

import java.util.List;

public interface IProductService {

    ServerResponse saveOrUpdate(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);


    ServerResponse manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchProduct(String productName, int productId, int pageNum, int pageSize);


}
