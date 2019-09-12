package com.mmall.controller.backend;

import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IFileService;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import com.mmall.util.PropertyUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 后台 强制登陆
 */
@Controller
@RequestMapping("manage/product/")
public class ProductManagerController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IFileService fileService;

    // 产品save / update
    @RequestMapping(value = "save.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session,
                                      Product product) {
        User user = getCurrentUser(session);
        ServerResponse response = userService.checkAdminRole(user);
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
        ServerResponse response = userService.checkAdminRole(user);
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
        ServerResponse response = userService.checkAdminRole(user);
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
        ServerResponse response = userService.checkAdminRole(user);
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
        ServerResponse response = userService.checkAdminRole(user);
        if (response.isSuccess()) {
            return productService.searchProduct(productName, productId, pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("no 权限");
        }
    }

    @RequestMapping(value = "upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file, path);
        String url = PropertyUtil.getProperty("ftp.server.http.prefix")
                + targetFileName;
        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);
        return ServerResponse.createBySuccess(fileMap);
    }

    //simditor
    @RequestMapping(value = "rich_text.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse richtextUpload(MultipartFile file,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file, path);
        String url = PropertyUtil.getProperty("ftp.server.http.prefix")
                + targetFileName;
        Map resultMap = Maps.newHashMap();
        if (StringUtils.isBlank(targetFileName)) {
            resultMap.put("success", false);
            resultMap.put("msg", "上传失败");
            return ServerResponse.createBySuccess(resultMap);
        }
        resultMap.put("success", true);
        resultMap.put("msg", "上传成功");
        resultMap.put("uri", targetFileName);
        resultMap.put("url", url);
        response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
        return ServerResponse.createBySuccess(resultMap);
    }
    private User getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return user;
    }
}
