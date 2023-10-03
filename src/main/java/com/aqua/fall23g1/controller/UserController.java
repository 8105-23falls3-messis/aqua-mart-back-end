package com.aqua.fall23g1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson2.JSONObject;
import com.aqua.fall23g1.entity.LoginReq;
import com.aqua.fall23g1.entity.User;
import com.aqua.fall23g1.service.UserService;
import com.aqua.fall23g1.utils.TokenUtil;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public int register(User user) {
        return userService.register(user);
    }

    @PostMapping("login")
    public JSONObject login(LoginReq loginReq) {
        JSONObject loginRes = new JSONObject();
        User user = userService.getUserByLoginData(loginReq);
        if (user == null) {
            loginRes.put("status", "fail");
            loginRes.put("msg", "email or password invalid");
        }
        String signToken = TokenUtil.sign(user);
        loginRes.put("status", "success");
        loginRes.put("token", signToken);
        return loginRes;
    }

    @GetMapping("/tokenTest")
    public JSONObject tokenTest() {
        JSONObject test = new JSONObject();
        test.put("msg", "you get access to other interface");
        return test;
    }
}
