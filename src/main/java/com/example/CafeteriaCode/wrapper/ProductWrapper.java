package com.example.CafeteriaCode.wrapper;

import lombok.Data;

@Data
public class ProductWrapper {

    Integer id;
    String name;
    String description;
    Integer price;
    String status;
    Integer categoryId;
    String categoryName;
     public ProductWrapper() {

     }
}
