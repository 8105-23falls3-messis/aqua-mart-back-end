package com.aqua.fall23g1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqua.fall23g1.entity.*;
import com.aqua.fall23g1.mapper.ProductMapper;
import com.aqua.fall23g1.service.ProductService;
import com.github.pagehelper.PageHelper;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

	@Override
	public void addProduct(Product product) {
		productMapper.insertProductData(product);
	}

	@Override
	public void updateProduct(Product product) {
		productMapper.updateProductData(product);
	}

	@Override
	public void deleteProduct(int id) {
		productMapper.deleteProductData(id);
	}

	@Override
	public List<Product> listProducts(TestReqParam param) {
		PageHelper.startPage(param.getPageNum(),param.getPageSize());
		return productMapper.getProducts();
	}

	@Override
	public Product getProduct(int id) {
		return productMapper.getProduct(id);
	}
	
}
