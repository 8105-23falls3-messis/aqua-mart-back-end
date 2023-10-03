package com.aqua.fall23g1.utils;

import com.alibaba.fastjson2.JSONObject;

public class JSONUtil {

    public static JSONObject resp(int status, String msg, Object obj) {
        JSONObject resp = new JSONObject();
        resp.put("status", status);
        resp.put("msg", msg);
        resp.put("content", obj);
        return resp;
    }
}
