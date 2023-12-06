package com.aqua.fall23g1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.aqua.fall23g1.controller.ImageController;
import com.aqua.fall23g1.entity.Category;
import com.aqua.fall23g1.entity.Image;
import com.aqua.fall23g1.entity.Product;
import com.aqua.fall23g1.entity.ProductListReqParam;
import com.aqua.fall23g1.mapper.ImageMapper;
import com.aqua.fall23g1.mapper.ProductMapper;
import com.aqua.fall23g1.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
      
    @Autowired
    private ImageMapper imageMapper;

	@Override
	public void addProduct(Product product) {
		
		productMapper.insertProductData(product);
		String[] split;
		for(Image newImage: product.getImages()) {
			
			if (newImage.getUrl().contains("/")) {
				split  = newImage.getUrl().split("/");		
			}else {
				split  = newImage.getUrl().split("\\\\");				
			}

			
			
			String url = MvcUriComponentsBuilder
					.fromMethodName(ImageController.class, "getFile",split[1] , newImage.getFileName().toString()).build().toString();
			Image image =new Image();
			image.setCover(false);
			image.setFileName(newImage.getFileName());
			image.setContenType(newImage.getContenType());
			image.setUrl(url);
			image.setProduct(product);	
			imageMapper.insertImageData(image);
		}
	}
	
	@Override
	public void addImagePathToProduct(MultipartFile newImage, Product product) {
		

			String url = MvcUriComponentsBuilder
					.fromMethodName(ImageController.class, "getFile", product.getId(),newImage.getOriginalFilename().toString()).build().toString();
			Image image =new Image();
			image.setCover(false);
			image.setFileName(newImage.getOriginalFilename());
			image.setContenType(newImage.getContentType());
			image.setUrl(url);
			image.setProduct(product);	
			imageMapper.insertImageData(image);
		
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
    public PageInfo<Product> listProducts(ProductListReqParam param) {
		PageHelper.startPage(param.getPageNum(),param.getPageSize());
        List<Product> products = productMapper.getProducts(param);
        return new PageInfo<>(products);
	}

	@Override
	public Product getProduct(int id) {
		return productMapper.getProduct(id);
	}

	@Override
	public List<Category> listCategories() {
		// TODO Auto-generated method stub
		return productMapper.getCategories();
	}

	@Override
	public void addImageToProduct(Image image) {
		String url = MvcUriComponentsBuilder
				.fromMethodName(ImageController.class, "getFile", image.getFileName().toString()).build().toString();
		image.setUrl(url);
		imageMapper.insertImageData( image);
		
	}

	@Override
	public List<Product> getProductsByUser(int idUser) {
		return productMapper.getProductsByUser(idUser);
	}
	
}
