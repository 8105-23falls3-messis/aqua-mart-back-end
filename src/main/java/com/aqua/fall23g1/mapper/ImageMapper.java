package com.aqua.fall23g1.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aqua.fall23g1.entity.Image;

@Repository
public interface ImageMapper {

    int insertImageData(Image image);
    
    boolean deleteImageData(int id);
    
    List<Image>getImagesByProduct(int idProduct);
    
}
