package com.example.CafeteriaCode.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.CafeteriaCode.POJO.Category;
import com.example.CafeteriaCode.constants.CafeConstants;
import com.example.CafeteriaCode.rest.CategoryRest;
import com.example.CafeteriaCode.service.CategoryService;
import com.example.CafeteriaCode.utils.CafeUtils;

@RestController
public class CategoryRestImpl implements CategoryRest {
	
	@Autowired
	CategoryService categoryService;

	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
		try {
			
			return categoryService.addNewCategory(requestMap);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
     try {
			
			return categoryService.getAllCategory(filterValue);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
       return new ResponseEntity<List<Category>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
