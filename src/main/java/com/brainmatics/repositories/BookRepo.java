package com.brainmatics.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.brainmatics.entity.Book;

public interface BookRepo extends PagingAndSortingRepository<Book, Long> {
	
	@Query("SELECT b FROM Book b WHERE b.title LIKE :title")
	public List<Book> findAllByTitle(@Param("title") String title, Pageable pageable);
	
	public Book findByCode(String code);
}
