package com.example.CafeteriaCode.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

	ResponseEntity<String> addNewCategory(Map<String, String> requestMap); 

}
