package com.aqua.fall23g1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aqua.fall23g1.entity.Product;
import com.aqua.fall23g1.entity.TestReqParam;
import com.aqua.fall23g1.service.ProductService;
import com.github.pagehelper.PageInfo;

@SpringBootTest
class Fall23g1ApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    void testPaginationOfProducts() {
        TestReqParam testReqParam = new TestReqParam();
        testReqParam.setPageNum(1);
        testReqParam.setPageSize(10);
        PageInfo<Product> productPageInfo = productService.listProducts(testReqParam);
        System.out.println(productPageInfo);
    }

}
