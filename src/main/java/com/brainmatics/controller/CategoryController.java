package com.brainmatics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brainmatics.entity.Category;
import com.brainmatics.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public Iterable<Category> findAll(){
		return categoryService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Category findById(@PathVariable("id") long id) {
		return categoryService.findById(id).get();
	}
	
	@PostMapping
	public Category saveCategory(@RequestBody Category category) {
		return categoryService.save(category);
	}
}
