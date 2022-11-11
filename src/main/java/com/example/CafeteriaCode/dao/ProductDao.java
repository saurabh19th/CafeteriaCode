package com.example.CafeteriaCode.dao;

import com.example.CafeteriaCode.POJO.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductDao extends JpaRepository<Product,Integer> {
}
