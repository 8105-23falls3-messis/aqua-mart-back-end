package com.aqua.fall23g1.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson2.JSONObject;
import com.aqua.fall23g1.mapper.UserMapper;
import com.aqua.fall23g1.utils.TokenUtil;

@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    /***
     * Handler call this method automatically before the processing of request
     */
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        System.out.println("execute handler preHandle method");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        System.out.println(request.getRequestURI());
        // if (request.getRequestURI().equals("/swagger-ui.html")) {
        // return true;
        // }
        String token = request.getHeader("token");
        if (token != null) {
            int tokenCount = userMapper.getTokenCount(token);
            if (0 == tokenCount) {
                JSONObject json = new JSONObject();
                json.put("msg", "token verify fail");
                json.put("code", "500");
                response.getWriter().append(json.toJSONString());
                System.out.println("Token invalid, please login and get a new token!");
                return false;
            }
            boolean result = TokenUtil.verify(token);
            if (result) {
                System.out.println("Get access from filter");
                return true;
            }
        }
        response.setContentType("application/json; charset=utf-8");
        try {
            JSONObject json = new JSONObject();
            json.put("msg", "token verify fail");
            json.put("code", "500");
            response.getWriter().append(json.toJSONString());
            System.out.println("verify fail, can't get access from filter");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        // if return false, the processing will be refused
        // if return true, the processing will continue (to controller)
        return false;
    }
}
