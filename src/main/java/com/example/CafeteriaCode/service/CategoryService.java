package com.example.CafeteriaCode.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;


public interface CategoryService {

	ResponseEntity<String> addNewCategory(Map<String, String> requestMap); 

}
