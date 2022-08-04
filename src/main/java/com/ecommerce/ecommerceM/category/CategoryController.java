package com.ecommerce.ecommerceM.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerceM.autoidgenerator.SequenceGeneratorService;

@RestController
public class CategoryController {

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	CategoryService categoryService;

	@PostMapping("/addCategory")
	public CategoryModel addCategory(@RequestBody CategoryModel model) {
		model.setId(sequenceGeneratorService.getSequenceNumber(CategoryModel.SEQUENCE_NAME));

		return categoryService.addCategory(model);
	}

	@GetMapping("/getAllCategory")
	public List<CategoryModel> getAllCategory() {
		List<CategoryModel> list = categoryService.getAllCategory();
		return list;
	}

	@GetMapping("/getCategoryById/{category_id}")
	public List<Object> getCategoryById(@PathVariable("category_id") int id) {
		List<Object> list = categoryService.getCategoryById(id);

		return list;

	}

	@GetMapping("/getCategoryByName/{category_name}")
	public List<Object> getCategoryByName(@PathVariable("category_name") String category_name) {
		List<Object> list = categoryService.getCategoryByName(category_name);
		return list;
	}

	@DeleteMapping("/deleteCategoryById/{id}")
	public void deleteCategoryById(@PathVariable("id") int id) {

		categoryService.deleteCategoryById(id);
	}

}