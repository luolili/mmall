package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
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
@RequestMapping("product/")
public class ProductController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;

    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, Integer productId) {
        return productService.getProductDetail(productId);

    }

    @RequestMapping(value = "search.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> query(@RequestParam(value = "keyword", required = false) String keyword,
                                          @RequestParam(value = "productName") String productName,
                                          // @RequestParam(value = "productId") int productId,
                                          @RequestParam(value = "categoryId") int categoryId,
                                          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                          @RequestParam(value = "orderBy", defaultValue = "") String orderBy
    ) {
        return productService.getProductByKeywordCategory(keyword, categoryId, pageNum, pageSize, orderBy);
    }


}
