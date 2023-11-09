package com.aqua.fall23g1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.aqua.fall23g1.controller.ImageController;
import com.aqua.fall23g1.entity.*;
import com.aqua.fall23g1.mapper.ImageMapper;
import com.aqua.fall23g1.mapper.ProductMapper;
import com.aqua.fall23g1.service.ProductService;
import com.github.pagehelper.PageHelper;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
      
    @Autowired
    private ImageMapper imageMapper;

	@Override
	public void addProduct(Product product) {

		
		
		String url = MvcUriComponentsBuilder
				.fromMethodName(ImageController.class, "getFile", product.getImage().getFileName().toString()).build().toString();
		
		Image image =new Image();
		image.setFileName(product.getImage().getFileName());
		image.setType(product.getImage().getType());
		image.setUrl(url);;

		imageMapper.insertImageData(image);
		product.setImage(image);
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
