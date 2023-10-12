package com.aqua.fall23g1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson2.JSONObject;
import com.aqua.fall23g1.constant.Status;
import com.aqua.fall23g1.entity.LoginReq;
import com.aqua.fall23g1.entity.Role;
import com.aqua.fall23g1.entity.TestReqParam;
import com.aqua.fall23g1.entity.User;
import com.aqua.fall23g1.service.UserService;
import com.aqua.fall23g1.utils.JSONUtil;
import com.aqua.fall23g1.utils.TokenUtil;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("roles")
    public JSONObject getAllRoles() {
        List<Role> roles = userService.getAllRoles();
        return JSONUtil.resp(Status.SUCCESS, "success", roles);
    }

    @PostMapping("register")
    public JSONObject register(@RequestBody User user) {
        JSONObject resp;
        int count = userService.countUser(user);
        // An email can have each role only once
        if (0 != count) {
            resp = JSONUtil.resp(Status.FAILED, "Account exist!", null);
            return resp;
        }
        int register = userService.register(user);
        if (register == 0) {
            resp = JSONUtil.resp(Status.FAILED, "Registration failed!", null);
        } else {
            resp = JSONUtil.resp(Status.SUCCESS, "Registration succeed.", null);
        }
        return resp;
    }

    @PostMapping("login")
    public JSONObject login(@RequestBody LoginReq loginReq) {
        JSONObject resp;
        User user = userService.getUserByLoginData(loginReq);
        if (user == null) {
            resp = JSONUtil.resp(Status.FAILED, "Email or password invalid.", null);
        } else {
            String signToken = TokenUtil.sign(user);
            JSONObject body = new JSONObject();
            body.put("token", signToken);
            body.put("user", user);
            resp = JSONUtil.resp(Status.SUCCESS, "Login succeed.", body);
        }
        return resp;
    }

    @PostMapping("loginOut")
    public JSONObject loginOut(@RequestBody LoginReq loginReq) {
        // TODO
        return null;
    }

    // for Cynthia
    @GetMapping("testPaging")
    public JSONObject testPaging(@RequestBody TestReqParam param) {
        // use role to test paging
        List<Role> allRolesByPage = userService.getAllRolesByPage(param);
        return JSONUtil.resp(Status.SUCCESS, "success", allRolesByPage);
    }
}
