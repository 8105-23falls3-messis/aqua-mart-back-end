package com.aqua.fall23g1.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aqua.fall23g1.entity.Category;
import com.aqua.fall23g1.entity.Image;
import com.aqua.fall23g1.entity.Product;
import com.aqua.fall23g1.entity.ProductListReqParam;
import com.github.pagehelper.PageInfo;

public interface ProductService {

	void addProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(int id);

    PageInfo<Product> listProducts(ProductListReqParam param);
	
	List<Category> listCategories();
	
	Category getCategory(int idCategory);

	Product getProduct(int id);
	
	void addImageToProduct(Image image);
	
	void addImagePathToProduct(MultipartFile newImage, Product product);
	
	List<Product> getProductsByUser(int idUser);

}
