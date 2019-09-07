package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 后台 强制登陆
 */
@Controller
@RequestMapping("manage/product/")
public class ProductManagerController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;


    //管理员的login
    @RequestMapping(value = "save.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session,
                                      Product product) {
        User user = getCurrentUser(session);
        ServerResponse<User> response = userService.checkAdminRole(user);
        if (response.isSuccess()) {
            return productService.saveOrUpdate(product);
        } else {
            return ServerResponse.createByErrorMessage("no 权限");
        }
    }

    @RequestMapping(value = "set_sale_status.do", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse<String> setSaleStatus(HttpSession session,
                                                Integer status, Integer productId) {
        User user = getCurrentUser(session);
        ServerResponse<User> response = userService.checkAdminRole(user);
        if (response.isSuccess()) {
            return productService.setSaleStatus(productId, status);
        } else {
            return ServerResponse.createByErrorMessage("no 权限");
        }
    }

    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, Integer productId) {
        User user = getCurrentUser(session);
        ServerResponse<User> response = userService.checkAdminRole(user);
        if (response.isSuccess()) {
            return productService.getDetail(productId);
        } else {
            return ServerResponse.createByErrorMessage("no 权限");
        }
    }

    @RequestMapping(value = "get_deep_category.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getCategoryAndDeepCategory(HttpSession session,
                                                     @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = getCurrentUser(session);
        ServerResponse<User> response = userService.checkAdminRole(user);
        if (response.isSuccess()) {
            return categoryService.selectCategoryAndChildrenById(categoryId);
        } else {
            return ServerResponse.createByErrorMessage("no 权限");
        }
    }

    private User getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return user;
    }
}
