package com.example.CafeteriaCode.dao;

import com.example.CafeteriaCode.POJO.Product;
import com.example.CafeteriaCode.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductDao extends JpaRepository<Product,Integer> {

    List<ProductWrapper> getAllProduct();
}
