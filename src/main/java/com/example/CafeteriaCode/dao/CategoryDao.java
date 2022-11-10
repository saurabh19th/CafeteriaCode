package com.example.CafeteriaCode.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CafeteriaCode.POJO.Category;

public interface CategoryDao extends JpaRepository<Category,Integer> {
	
	List<Category> getAllCategory();

}
