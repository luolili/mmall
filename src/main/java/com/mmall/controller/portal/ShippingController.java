package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;
import com.mmall.pojo.User;
import com.mmall.service.ICartService;
import com.mmall.service.IProductService;
import com.mmall.service.IShippingService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("shipping/")
public class ShippingController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IShippingService shippingService;

    @Autowired
    private ICartService cartService;

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addToCart(HttpSession session, Shipping shipping) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return shippingService.add(currentUser.getId(), shipping);
    }

    @RequestMapping(value = "update.do", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse update(HttpSession session, Integer count, Integer productId) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return cartService.update(currentUser.getId(), count, productId);
    }

    @RequestMapping(value = "delete.do", method = RequestMethod.DELETE)
    @ResponseBody
    public ServerResponse deleteProduct(HttpSession session, Integer shippingId) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return shippingService.deleteShipping(currentUser.getId(), shippingId);
    }

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse query(HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return cartService.list(currentUser.getId());
    }

    @RequestMapping(value = "select_all.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse selectAll(HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return cartService.selectOrUnselect(currentUser.getId(), Const.Cart.CHECKED, null);
    }

    //全不选
    @RequestMapping(value = "unselect_all.do", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse unselectAll(HttpSession session, Integer productId) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return cartService.selectOrUnselect(currentUser.getId(), Const.Cart.UN_CHECKED, null);
    }

    //选一个
    @RequestMapping(value = "select_one.do", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse selectOne(HttpSession session, Integer productId) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return cartService.selectOrUnselect(currentUser.getId(), Const.Cart.CHECKED, productId);
    }

    //取消选一个
    @RequestMapping(value = "unselect_one.do", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse unselectOne(HttpSession session, Integer productId) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return cartService.selectOrUnselect(currentUser.getId(), Const.Cart.UN_CHECKED, productId);
    }

    //获取用户的购物车里面的产品count
    @RequestMapping(value = "get_cart_product_count.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Integer> getCartProductCount(HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createBySuccess(0);
        }
        return cartService.getCartProductCount(currentUser.getId());
    }
}
