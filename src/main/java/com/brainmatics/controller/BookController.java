package com.brainmatics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brainmatics.dto.SearchBookForm;
import com.brainmatics.entity.Book;
import com.brainmatics.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping(value = "{/id}")
	public Book findById(@PathVariable("id") long id) {
		return bookService.findById(id).get();
	}
	
	@GetMapping
	public Iterable<Book> findAll(){
		return bookService.findAll();
	}
	
	@GetMapping(value = "/page/{page}")
	public List<Book> findAll(@PathVariable("page") int page){
		return bookService.findAll(page);
	}
	
	@PostMapping(value = "/search")
	public List<Book> findByTitle(@RequestBody SearchBookForm form){
		return bookService.findAllByTitle(form.getTitle(), form.getPage());
	}
	
	@PostMapping
	public Book saveBook(@RequestBody Book book) {
		return bookService.save(book);
	}
}
