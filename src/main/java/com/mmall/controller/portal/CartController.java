package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
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

    @RequestMapping(value = "add.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse addToCart(HttpSession session, Integer count, Integer productId) {

        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return userService.getInformation(currentUser.getId());

    }

    //可以 不必加 method = RequestMethod.POST
    @RequestMapping(value = "search.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse query(@RequestParam(value = "keyword", required = false) String keyword,
                                @RequestParam(value = "categoryId") int categoryId,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(value = "orderBy", defaultValue = "") String orderBy
    ) {
        return ServerResponse.createBySuccess(productService.getProductByKeywordCategory(keyword, categoryId, pageNum, pageSize, orderBy));

    }


}
