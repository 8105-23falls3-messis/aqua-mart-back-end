package com.aqua.fall23g1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestReqParam {
    private String condition1;
    private String condition2;
    // etc..
    // key param
    private int pageNum;
    private int pageSize;

}
