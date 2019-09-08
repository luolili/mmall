package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("order/")
public class OrderController {

    public ServerResponse pay(HttpSession session, Long orderNo, HttpServletRequest request) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "需要登陆");
        }
        String path = request.getSession().getServletContext().getRealPath("upload");

    }
}
