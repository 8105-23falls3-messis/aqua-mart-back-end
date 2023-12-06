package com.aqua.fall23g1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListReqParam {

    private String title;
    private int categoryId;
    private int pageNum;
    private int pageSize;

}
