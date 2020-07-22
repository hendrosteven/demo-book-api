package com.brainmatics.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brainmatics.entity.Category;

public interface CategoryRepo extends CrudRepository<Category, Long>{

	
}
