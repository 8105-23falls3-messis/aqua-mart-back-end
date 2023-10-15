package com.aqua.fall23g1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	private int id;
	private String name;
	private String type;
	private byte []pic;

}
