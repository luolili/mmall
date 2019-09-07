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
    @RequestMapping(value = "add_product.do", method = RequestMethod.POST)
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

    @RequestMapping(value = "set_category_name.do", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session,
                                          String categoryName, Integer categoryId) {
        User user = getCurrentUser(session);
        ServerResponse<User> response = userService.checkAdminRole(user);
        if (response.isSuccess()) {
            //admin yes
            return categoryService.updateCategoryName(categoryId, categoryName);
        } else {
            return ServerResponse.createByErrorMessage("no 权限");
        }
    }

    @RequestMapping(value = "get_category.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session,
                                                      @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = getCurrentUser(session);
        ServerResponse<User> response = userService.checkAdminRole(user);
        if (response.isSuccess()) {
            return categoryService.getChildrenParallelCategory(categoryId);
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
