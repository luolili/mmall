package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/user/")
public class UserManagerController {

    @Autowired
    private IUserService userService;

    //管理员的login
    @RequestMapping(value = "login.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session, HttpServletResponse resp) {
        ServerResponse<User> response = userService.login(username, password);
        if (response.isSuccess()) {
            User user = response.getData();
            if (Const.Role.ROLE_ADMIN == user.getRole()) {
                //session.setAttribute(Const.CURRENT_USER, user);
                CookieUtil.writeLoginToken(resp, session.getId());
                RedisShardedPoolUtil.setEx(session.getId(), JsonUtil.obj2String(response.getData()), 6000);
                return response;
            } else {
                return ServerResponse.createByErrorMessage("不是管理员, 无法登陆");
            }

        }
        return response;
    }

}
