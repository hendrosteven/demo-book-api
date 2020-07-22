package com.brainmatics.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryForm {
	
	@NotEmpty(message="Category name is required")
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
