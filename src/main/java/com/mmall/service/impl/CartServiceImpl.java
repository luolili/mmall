package com.mmall.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CartMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Cart;
import com.mmall.pojo.Product;
import com.mmall.service.ICartService;
import com.mmall.util.BigDecimalUtil;
import com.mmall.util.PropertyUtil;
import com.mmall.vo.CartProductVO;
import com.mmall.vo.CartVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    //数量的校验
    @Override
    public ServerResponse add(Integer userId, Integer count, Integer productId) {
        if (productId == null || count == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        if (cart == null) {
            Cart cartItem = new Cart();
            cartItem.setQuantity(count);
            cartItem.setChecked(1);
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartMapper.insert(cartItem);
        } else {
            cart.setQuantity(cart.getQuantity() + count);
            cartMapper.updateByPrimaryKeySelective(cart);

        }
        CartVO cartVO = getCartVO(userId);
        return ServerResponse.createBySuccess(cartVO);
    }

    @Override
    public ServerResponse update(Integer userId, Integer count, Integer productId) {
        if (productId == null || count == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        if (cart != null) {
            cart.setQuantity(count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        CartVO cartVO = getCartVO(userId);
        return ServerResponse.createBySuccess(cartVO);
    }

    @Override
    public ServerResponse deleteProduct(Integer userId, String productIds) {
        List<String> productIdList = Splitter.on(",").splitToList(productIds);
        if (CollectionUtils.isEmpty(productIdList)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        cartMapper.deleteCartByUserIdProductIds(userId, productIdList);
        CartVO cartVO = getCartVO(userId);
        return ServerResponse.createBySuccess(cartVO);
    }

    @Override
    public ServerResponse list(Integer userId) {
        CartVO cartVO = getCartVO(userId);
        return ServerResponse.createBySuccess(cartVO);
    }

    @Override
    public ServerResponse selectOrUnselect(Integer userId, Integer checked, Integer productId) {
        cartMapper.checkedOrUncheckedProduct(userId, checked, productId);
        return list(userId);
    }

    @Override
    public ServerResponse<Integer> getCartProductCount(Integer userId) {
        if (userId == null) {
            return ServerResponse.createBySuccess(0);
        }
        int result = cartMapper.selectCartProductCountByUserId(userId);
        return ServerResponse.createBySuccess(result);
    }

    private CartVO getCartVO(Integer userId) {
        CartVO cartVO = new CartVO();
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        List<CartProductVO> cartProductVOList = Lists.newArrayList();
        BigDecimal cartTotalPrice = new BigDecimal("0");

        if (CollectionUtils.isNotEmpty(cartList)) {
            for (Cart cart : cartList) {
                CartProductVO cartProductVO = new CartProductVO();
                cartProductVO.setId(cart.getId());
                cartProductVO.setUserId(cart.getUserId());
                cartProductVO.setProductId(cart.getProductId());
                Product product = productMapper.selectByPrimaryKey(cart.getProductId());

                if (product != null) {
                    cartProductVO.setProductMainImage(product.getMainImage());
                    cartProductVO.setProductName(product.getName());
                    cartProductVO.setProductSubtile(product.getSubtitle());
                    cartProductVO.setProductStatus(product.getStatus());
                    cartProductVO.setProductPrice(product.getPrice());
                    cartProductVO.setProductStock(product.getStock());
                    int buyLimitCount;

                    if (product.getStock() >= cart.getQuantity()) {
                        buyLimitCount = cart.getQuantity();
                        cartProductVO.setLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
                    } else {
                        buyLimitCount = product.getStock();
                        //超过stock
                        cartProductVO.setLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
                        //更新库存
                        Cart cartForQuantity = new Cart();
                        cartForQuantity.setId(cart.getId());
                        cartForQuantity.setQuantity(buyLimitCount);
                        cartMapper.updateByPrimaryKeySelective(cartForQuantity);
                    }
                    cartProductVO.setQuantity(buyLimitCount);
                    // 商品的总价
                    cartProductVO.setProductTotalPrice(BigDecimalUtil.multiply(
                            product.getPrice().doubleValue(), cart.getQuantity()));
                    //是否勾选
                    cartProductVO.setProductChecked(cart.getChecked());
                }

                if (cart.getChecked() == Const.Cart.CHECKED) {
                    //如果勾选，增加到整个购物车的总价里面
                    cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(),
                            cartProductVO.getProductPrice().doubleValue());
                }

                cartProductVOList.add(cartProductVO);
            }

        }
        cartVO.setCartTotalPrice(cartTotalPrice);
        cartVO.setCartProductVOList(cartProductVOList);
        boolean checkedStatus = getAllCheckedStatus(userId);
        cartVO.setAllChecked(checkedStatus);
        cartVO.setImageHost(PropertyUtil.getProperty("ftp.server.http.prefix"));
        return cartVO;
    }

    private boolean getAllCheckedStatus(Integer userId) {
        if (userId == null) {
            return false;
        }
        return cartMapper.selectCartProductCheckedStatusByUserId(userId) == 0;
    }
}
