package com.mmall.controller.common.interceptor;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("post handle");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getSimpleName();
        StringBuffer sb = new StringBuffer();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> it = parameterMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String[]> entry = it.next();
            String key = entry.getKey();
            String[] value = entry.getValue();
            sb.append(key).append("=").append(Arrays.toString(value));
        }

        User user = null;
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isNotEmpty(loginToken)) {
            String jsonStr = RedisShardedPoolUtil.get(loginToken);
            user = JsonUtil.string2Obj(jsonStr, User.class);

        }
        if (user == null || (user.getRole().intValue() != Const.Role.ROLE_ADMIN)) {
            // 1 reset response,否则报ex : getWriter
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;UTF-8");
            PrintWriter writer = response.getWriter();

            if (user == null) {
                writer.println(JsonUtil.obj2String(
                        ServerResponse.createByErrorMessage("用户未登陆")));

            } else {
                writer.println(JsonUtil.obj2String(
                        ServerResponse.createByErrorMessage("需要管理员权限")));
            }
            writer.flush();
            writer.close();
            return false;//不进入controller
        }

        return true;//true表示要经过controller
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("post handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("after above all");
    }
}
