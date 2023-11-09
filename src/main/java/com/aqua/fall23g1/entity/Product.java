package com.aqua.fall23g1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String title;
    private String brand;
    private String cost;
    private String description;
    //@Column(name=”DESC”)
    private User user;
    private Image image;
    private Boolean active;

}
