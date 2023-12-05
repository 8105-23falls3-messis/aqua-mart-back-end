package com.aqua.fall23g1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	  private int id;
	  private String fileName;
	  private String contenType;
	  private String url;
	  private Boolean cover;
	  private Product product;
	  
	  public Image(String name, String url, String contenType) {
		  this.fileName=name;
		  this.url=url;
		  this.contenType=contenType;
	  }
	  
	  public Image(String name, String url) {
		  this.fileName=name;
		  this.url=url;
	  }
	  
	  
}
