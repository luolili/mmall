package com.mmall.controller.portal;

import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("order/")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "create.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse create(HttpSession session, Integer shippingId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return ServerResponse.createBySuccess(orderService.createOrder(user.getId(), shippingId));

    }

    @RequestMapping(value = "cancel.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse cancel(HttpSession session, Long orderNo) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return ServerResponse.createBySuccess(orderService.cancel(user.getId(), orderNo));
    }

    @RequestMapping(value = "get_cart_product.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getCartProduct(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return ServerResponse.createBySuccess(orderService.getCartProduct(user.getId()));
    }

    @RequestMapping(value = "get_order_detail.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getOrderDetail(HttpSession session, Long orderNo) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return ServerResponse.createBySuccess(orderService.getOrderDetail(user.getId(), orderNo));
    }

    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getOrders(HttpSession session, Long orderNo,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        return ServerResponse.createBySuccess(orderService.getOrderList(user.getId(), pageNum, pageSize));
    }

    @RequestMapping(value = "pay.do", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse pay(HttpSession session, Long orderNo, HttpServletRequest request) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        String path = request.getSession().getServletContext().getRealPath("upload");

        return ServerResponse.createBySuccess(
                orderService.pay(orderNo, currentUser.getId(), path));
    }

    @RequestMapping(value = "alipay_callback.do", method = RequestMethod.PUT)
    @ResponseBody
    public Object alipayCallback(HttpServletRequest request) {
        Map<String, String> params = Maps.newHashMap();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Iterator<String> it = parameterMap.keySet().iterator();
        for (; it.hasNext(); ) {
            String name = (String) it.next();
            String[] values = (String[]) parameterMap.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : values[i] + ",";
            }
            params.put(name, valueStr);
        }
        logger.info("支付宝callback: sign:{}, trade_status:{},参数：{}", params.get("sign"),
                params.get("trade_status"), params.toString());
        params.remove("sign_type");
        return null;


    }
}
