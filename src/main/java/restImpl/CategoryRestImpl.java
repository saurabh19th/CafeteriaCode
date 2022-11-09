package restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import rest.CategoryRest;
import service.CategoryService;
import utils.CafeUtils;

@RestController
public class CategoryRestImpl implements CategoryRest {
	
	@Autowired
	CategoryService categoryService;

	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
		try {
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity();
	}

}
