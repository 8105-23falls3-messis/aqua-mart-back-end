package com.aqua.fall23g1.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.aqua.fall23g1.entity.Image;

public interface ImageService {
	
	  public void init();

	  public Image save(MultipartFile file,  Long timestamp);

	  public Resource load(String subfolder, String filename);

	  public void deleteAll();

	  public Stream<Path> loadAll();
	  
	  public void deleteId(int id);
	  
	  public List<Image>getImageByProduct(int productId);

}
