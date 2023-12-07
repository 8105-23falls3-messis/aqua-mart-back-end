package com.aqua.fall23g1.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	
	private final Path root = Paths.get("uploads");

	@Override
	public void addProduct(Product product) {

		productMapper.insertProductData(product);
		this.insertImageDb(product);
	}

	public void insertImageDb(Product product) {
		String[] split;
		for (Image newImage : product.getImages()) {

			if (newImage.getUrl().contains("/")) {
				split = newImage.getUrl().split("/");
			} else {
				split = newImage.getUrl().split("\\\\");
			}

			String url = MvcUriComponentsBuilder
					.fromMethodName(ImageController.class, "getFile", split[1], newImage.getFileName().toString())
					.build().toString();
			Image image = new Image();
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

		String url = MvcUriComponentsBuilder.fromMethodName(ImageController.class, "getFile", product.getId(),
				newImage.getOriginalFilename().toString()).build().toString();
		Image image = new Image();
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
		if(!product.getImages().isEmpty()) {
			imageMapper.deleteImageData(product.getId());
			
			for(Image image:product.getImages()) {
				String[] split;
				if (image.getUrl().contains("/")) {
					split = image.getUrl().split("/");
				} else {
					split = image.getUrl().split("\\\\");
				}
				
				Path path=  Paths.get(root +"/" + split[1]);
				try {
					Files.deleteIfExists(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}			
			this.insertImageDb(product);
		}

	}

	@Override
	public void deleteProduct(int id) {
		productMapper.deleteProductData(id);
	}

	@Override
	public PageInfo<Product> listProducts(ProductListReqParam param) {
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
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
	public Category getCategory(int idCategory) {
		return productMapper.getCategory(idCategory);
	}

	@Override
	public void addImageToProduct(Image image) {
		String url = MvcUriComponentsBuilder
				.fromMethodName(ImageController.class, "getFile", image.getFileName().toString()).build().toString();
		image.setUrl(url);
		imageMapper.insertImageData(image);

	}

	@Override
	public List<Product> getProductsByUser(int idUser) {
		return productMapper.getProductsByUser(idUser);
	}

}
