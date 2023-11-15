package com.aqua.fall23g1.entity;



import java.util.List;

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
    private float cost;
    private String description;
    private Category category;
    //@Column(name=”DESC”)
    private User user;
    private List<Image> images;
    private Boolean active;
}
