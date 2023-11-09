package com.aqua.fall23g1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson2.JSONObject;
import com.aqua.fall23g1.constant.Status;
import com.aqua.fall23g1.entity.Product;
import com.aqua.fall23g1.entity.TestReqParam;
import com.aqua.fall23g1.service.ImageStorageService;
import com.aqua.fall23g1.service.ProductService;
import com.aqua.fall23g1.utils.JSONUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/product")
@Tag(name = "Product Management")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ImageStorageService imageStorageService;

	private Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Operation(summary ="Get all products")
	@GetMapping("list")
	public JSONObject getAllProducts(@RequestBody TestReqParam param) {
		List<Product> products = productService.listProducts(param);
		return JSONUtil.resp(Status.SUCCESS, "success", products);
	}

	@Operation(summary ="Add product" , description ="To add a product, an image information must be sent")
	@PostMapping("add")
	public JSONObject addProduct(@RequestBody Product product) {
		JSONObject resp;
		JSONObject body = new JSONObject();

		try {
			product.setActive(true);
			productService.addProduct(product);
			body.put("add", true);
			resp = JSONUtil.resp(Status.SUCCESS, "Added successfully.", body);
		} catch (Exception ex) {
			body.put("add", false);
			body.put("message", ex.getMessage());
			resp = JSONUtil.resp(Status.FAILED, "Added failed.", body);
			logger.info(ex.getMessage());

		}
		return resp;
	}

	@Operation(summary ="Update product")
	@PutMapping("update")
	public JSONObject updateProduct(@RequestBody Product product) {
		JSONObject resp;
		JSONObject body = new JSONObject();
		body.put("id", product.getId());
		try {
			productService.updateProduct(product);
			body.put("update", true);
			resp = JSONUtil.resp(Status.SUCCESS, "Updated successfully.", body);
			logger.info("Product" + product.getId() + "updated successfully");
		} catch (Exception ex) {
			body.put("update", false);
			body.put("message", ex.getMessage());
			resp = JSONUtil.resp(Status.FAILED, "Updated failed.", body);
			logger.info(ex.getMessage());
		}
		return resp;
	}

	@Operation(summary ="Delete product")
	@DeleteMapping("delete/{id}")
	public JSONObject deleteProduct(@PathVariable("id") int id) {
		JSONObject resp;
		JSONObject body = new JSONObject();
		body.put("id", id);
		try {
			Product product =productService.getProduct(id);			
			productService.deleteProduct(id);
			imageStorageService.deleteId(product.getImage().getId());
			body.put("delete", true);
			resp = JSONUtil.resp(Status.SUCCESS, "Deleted successfully.", body);
			logger.info("Product " + id + " deleted successfully");
		} catch (Exception ex) {
			body.put("delete", false);
			body.put("message", ex.getMessage());
			resp = JSONUtil.resp(Status.FAILED, "Deleted failed.", null);
			logger.info(ex.getMessage());
		}
		return resp;
	}

	@Operation(summary ="Get product")
	@GetMapping("get/{id}")
	public JSONObject getProduct(@PathVariable("id") int id) {
		JSONObject resp;
		JSONObject body = new JSONObject();
		body.put("id", id);

		try {
			Product product = productService.getProduct(id);
			body.put("product", product);
			resp = JSONUtil.resp(Status.SUCCESS, "Get successfully.", body);
			logger.info("Product " + id + " deleted successfully");
		} catch (Exception ex) {
			body.put("product", null);
			body.put("message", ex.getMessage());
			resp = JSONUtil.resp(Status.FAILED, "Get failed.", body);
			logger.info(ex.getMessage());
		}
		return resp;
	}

}
