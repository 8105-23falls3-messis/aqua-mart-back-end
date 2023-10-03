package com.aqua.fall23g1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson2.JSONObject;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/tokenTest")
    public JSONObject tokenTest() {
        JSONObject test = new JSONObject();
        test.put("msg", "you get access to other interface");
        return test;
    }
}
