package com.brainmatics.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brainmatics.dto.ResponseData;
import com.brainmatics.dto.SearchBookForm;
import com.brainmatics.entity.Book;
import com.brainmatics.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") long id) {
		ResponseData response = new ResponseData();
		response.setStatus(true);
		response.setPayload(bookService.findById(id).get());
		return ResponseEntity.ok(response);
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
	public ResponseEntity<?> saveBook(@Valid @RequestBody Book book, Errors errors) {
		ResponseData response = new ResponseData();
		if(!errors.hasErrors()) {
			response.getMessages().add("Book saved");
			response.setStatus(true);
			response.setPayload(bookService.save(book));
			return ResponseEntity.ok(response);
		}else {
			for(ObjectError err: errors.getAllErrors()) {
				response.getMessages().add(err.getDefaultMessage());
			}
			response.setStatus(false);
			return ResponseEntity.ok(response);
		}
		
	}
}
