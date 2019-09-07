package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CartMapper;
import com.mmall.pojo.Cart;
import com.mmall.pojo.Product;
import com.mmall.service.ICartService;
import com.mmall.vo.CartProductVO;
import com.mmall.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;

    //数量的校验
    @Override
    public ServerResponse add(Integer userId, Integer count, Integer productId) {
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        int resultCount = 0;
        if (cart == null) {
            cart = new Cart();
            cart.setQuantity(count);
            cart.setChecked(1);
            cart.setUserId(userId);
            cart.setProductId(productId);
            resultCount = cartMapper.insert(cart);
            if (resultCount > 0) {
                return ServerResponse.createBySuccess("添加到cart成功");
            }
        } else {
            cart.setQuantity(cart.getQuantity() + count);
            resultCount = cartMapper.updateByPrimaryKeySelective(cart);
            if (resultCount > 0) {
                return ServerResponse.createBySuccess("添加到cart成功");
            }
        }
        return ServerResponse.createByErrorMessage("添加到cart失败");
    }

    private CartVO getCartVO(Integer userId) {
        CartVO cartVO = new CartVO();
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        List<CartProductVO> cartProductVOList = Lists.newArrayList();
        BigDecimal cartTotalPrice = new BigDecimal("0");


        return cartVO;


    }
}
