package com.brainmatics.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.brainmatics.entity.Book;
import com.brainmatics.repositories.BookRepo;

@Service("bookService")
@Transactional
public class BookService {

	@Autowired
	private BookRepo bookRepo;
	
	public Book save(Book book) {
		return bookRepo.save(book);
	}
	
	public Iterable<Book> findAll(){
		return bookRepo.findAll();
	}
	
	public List<Book> findAll(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return bookRepo.findAll(pageable).getContent();
	}
	
	public List<Book> findAllByTitle(String title, int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return bookRepo.findAllByTitle(title, pageable);
	}
	
	public Book findByCode(String code) {
		return bookRepo.findByCode(code);
	}
	
	public Optional<Book> findById(long id) {
		return bookRepo.findById(id);
	}
	
}
