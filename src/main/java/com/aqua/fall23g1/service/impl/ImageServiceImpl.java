package com.aqua.fall23g1.service.impl;

import com.aqua.fall23g1.entity.Image;
import com.aqua.fall23g1.mapper.ImageMapper;
import com.aqua.fall23g1.service.ImageService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService{
	
	private final Path root = Paths.get("uploads");
	
    @Autowired
    private ImageMapper imageMapper;

	  @Override
	  public void init() {
	    try {
	      Files.createDirectories(root);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }

	  @Override
	  public Image save(MultipartFile file, Long timestamp) {
		  Image image;
		  //Files.createDirectories(root);
	    try {
	    	Path path=  Paths.get(root +"/" + timestamp);
	    	Files.createDirectories(path);
	    	System.out.println("Directory is created!");
	    	
	    	System.out.println("PATH NAME" + path.getFileName());
	    	System.out.println("PATH NAME" + path.getRoot());
	    	
	      Files.copy(file.getInputStream(),path.resolve(file.getOriginalFilename()));
	      image = new Image(file.getOriginalFilename(),path.resolve(file.getOriginalFilename()).toString(),file.getContentType());
	      return image;
	    } catch (Exception e) {
	      if (e instanceof FileAlreadyExistsException) {
	        throw new RuntimeException("A file of that name already exists.");
	      }

	      throw new RuntimeException(e.getMessage());
	    }
	  }

	  @Override
	  public Resource load(String subfolder, String filename) {
	    try {
	
	      Path file = root.resolve(subfolder+ "/"+filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  @Override
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(root.toFile());
	  }

	  @Override
	  public Stream<Path> loadAll() {
	    try {
	      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }

	@Override
	public void deleteId(int id) {
		imageMapper.deleteImageData(id);
	}

	@Override
	public List<Image>getImageByProduct(int idProduct) {
		return imageMapper.getImagesByProduct(idProduct);	
	}

}
