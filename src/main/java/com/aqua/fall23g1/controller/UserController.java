package com.aqua.fall23g1.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson2.JSONObject;
import com.aqua.fall23g1.constant.Status;
import com.aqua.fall23g1.entity.*;
import com.aqua.fall23g1.service.UserService;
import com.aqua.fall23g1.utils.JSONUtil;
import com.aqua.fall23g1.utils.TokenUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "User Management")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary ="Get all roles without param")
    @GetMapping("roles")
    public JSONObject getAllRoles() {
        List<Role> roles = userService.getAllRoles();
        return JSONUtil.resp(Status.SUCCESS, "success", roles);
    }

    @Operation(summary ="Register interface")
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
            resp = JSONUtil.resp(Status.SUCCESS, "Registration successfully.", null);
        }
        return resp;
    }

    @Operation(summary ="Login interface")
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
            TokenHistory tokenHistory = new TokenHistory();
            tokenHistory.setToken(signToken);
            tokenHistory.setUserName(user.getFirstName() + " " + user.getLastName());
            // insert token history into database
            userService.addTokenHistory(tokenHistory);
            resp = JSONUtil.resp(Status.SUCCESS, "Login successfully.", body);
        }
        return resp;
    }

    @Operation(summary ="Logout interface")
    @PostMapping("logOut")
    public JSONObject loginOut(HttpServletRequest request) {
        String token = request.getHeader("token");
        userService.removeTokenHistory(token);
        return JSONUtil.resp(Status.SUCCESS, "Log out successfully.", null);
    }

    @Operation(summary ="Get the user profile")
    @GetMapping("userProfile")
    public JSONObject userProfile(@RequestParam("userId") String userId) {
        User user = userService.getUserById(userId);
        return JSONUtil.resp(Status.SUCCESS, "success", user);
    }

    @Operation(summary ="Update the user profile")
    @GetMapping("updateUser")
    public JSONObject updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return JSONUtil.resp(Status.SUCCESS, "success", null);
    }

    // for Cynthia
    @GetMapping("testPaging")
    public JSONObject testPaging(@RequestBody TestReqParam param) {
        // use role to test paging
        List<Role> allRolesByPage = userService.getAllRolesByPage(param);
        return JSONUtil.resp(Status.SUCCESS, "success", allRolesByPage);
    }
}
