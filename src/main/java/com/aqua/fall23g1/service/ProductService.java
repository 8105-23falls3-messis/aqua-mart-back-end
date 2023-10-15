package com.aqua.fall23g1.service;

import java.util.List;

import com.aqua.fall23g1.entity.*;

public interface ProductService {

	void addProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(int id);

	List<Product> listProducts(TestReqParam param);

	Product getProduct(int id);

}
