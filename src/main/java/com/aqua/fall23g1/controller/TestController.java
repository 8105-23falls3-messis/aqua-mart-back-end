package com.aqua.fall23g1.controller;

import com.aqua.fall23g1.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("insert")
    public int testInsertData(@RequestParam("data") String data) {
        return testService.testInsertData(data);
    }

}
