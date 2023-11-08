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
    
    public static JSONObject respImage(int status, String msg, Object obj, String originalFilename,String type, Long size) {
        JSONObject resp = new JSONObject();
        resp.put("status", status);
        resp.put("msg", msg);
        resp.put("content", obj);
        resp.put("filename", originalFilename);
        resp.put("type", type);
        resp.put("size", size);
        return resp;
    }
}
