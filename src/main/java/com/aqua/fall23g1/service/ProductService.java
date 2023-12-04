package com.aqua.fall23g1.service;

import java.util.List;

import com.aqua.fall23g1.entity.Category;
import com.aqua.fall23g1.entity.Image;
import com.aqua.fall23g1.entity.Product;
import com.aqua.fall23g1.entity.TestReqParam;
import com.github.pagehelper.PageInfo;

public interface ProductService {

	void addProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(int id);

    PageInfo<Product> listProducts(TestReqParam param);
	
	List<Category> listCategories();

	Product getProduct(int id);
	
	void addImageToProduct(Image image);
	
	List<Product> getProductsByUser(int idUser);

}
