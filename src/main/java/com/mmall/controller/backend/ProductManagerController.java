package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import com.mmall.vo.ProductDetailVO;
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

    // 产品save / update
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
    public ServerResponse<ProductDetailVO> getDetail(HttpSession session, Integer productId) {
        User user = getCurrentUser(session);
        ServerResponse<User> response = userService.checkAdminRole(user);
        if (response.isSuccess()) {
            return productService.manageProductDetail(productId);
        } else {
            return ServerResponse.createByErrorMessage("no 权限");
        }
    }

    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getProductList(HttpSession session,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = getCurrentUser(session);
        ServerResponse<User> response = userService.checkAdminRole(user);
        if (response.isSuccess()) {
            return productService.getProductList(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("no 权限");
        }
    }

    @RequestMapping(value = "search.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse query(HttpSession session,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "productName") String productName,
                                @RequestParam(value = "productId") int productId,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = getCurrentUser(session);
        ServerResponse<User> response = userService.checkAdminRole(user);
        if (response.isSuccess()) {
            return productService.searchProduct(productName, productId, pageNum, pageSize);
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
