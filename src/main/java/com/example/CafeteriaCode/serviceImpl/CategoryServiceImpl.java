package com.example.CafeteriaCode.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.CafeteriaCode.rest.CategoryRest;

import com.example.CafeteriaCode.POJO.Category;
import com.example.CafeteriaCode.constants.CafeConstants;
import com.example.CafeteriaCode.dao.CategoryDao;
import com.example.CafeteriaCode.service.CategoryService;
import com.example.CafeteriaCode.utils.CafeUtils;

@Service
public class CategoryServiceImpl implements CategoryRest {
	
	@Autowired
	CategoryDao categoryDao;
	

	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
		try {
			
			if(validateCategoryMap(requestMap,false)) {
				categoryDao.save(getCategoryFromMap(requestMap, false));
				return CafeUtils.getResponseEntity("Category Added Successfully",HttpStatus.OK);
			}

			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

	}


	private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
		
		if(requestMap.containsKey("name")) {
			if(requestMap.containsKey("id") && validateId) {
				return true;
			}
			else if(!validateId){
				return true;
			}
		}
		return false;
	}
	
	private Category getCategoryFromMap(Map<String,String> requestMap,Boolean isAdd) {
		
		Category category = new Category(); 
		if(isAdd) {
			category.setId(Integer.parseInt(requestMap.get("id")));
		}
		category.setName(requestMap.get("name"));
		return category;
	}

}