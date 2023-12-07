package com.aqua.fall23g1.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aqua.fall23g1.entity.Category;
import com.aqua.fall23g1.entity.Product;
import com.aqua.fall23g1.entity.ProductListReqParam;

@Repository
public interface ProductMapper {

    int insertProductData(Product product);
    
    int updateProductData(Product product);
    
    boolean deleteProductData(int id);

    List<Product> getProducts(ProductListReqParam param);
    
    List<Category>getCategories();
    
    Category getCategory(int idCateogry);
    
    Product getProduct(int id);
    
    List<Product> getProductsByUser(int idUser);

}
