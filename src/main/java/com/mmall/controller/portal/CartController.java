package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICartService;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("cart/")
public class CartController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;

    @Autowired
    private ICartService cartService;

    @RequestMapping(value = "add.do", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse addToCart(HttpSession session, Integer count, Integer productId) {

        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return cartService.add(currentUser.getId(), count, productId);
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

    @RequestMapping(value = "delete_product.do", method = RequestMethod.DELETE)
    @ResponseBody
    public ServerResponse deleteProduct(HttpSession session, String productIds) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return cartService.deleteProduct(currentUser.getId(), productIds);
    }

}
