package com.reactive.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.app.entities.Book;
import com.reactive.app.service.BookService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookControllers {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping
	public Mono<Book> createBook(@RequestBody Book book){
		return this.bookService.createBook(book);
	}
	
	@GetMapping
	public Flux<Book> getAllBooks(){
		return this.bookService.getAllBooks();
	}
	
	@GetMapping("/{bookId}")
	public Mono<Book> getBookById(@PathVariable Integer bookId){
		return this.bookService.getSingleBook(bookId);
	}
	
	@PutMapping("/{bookId}")
	public Mono<Book> updateBook(@RequestBody Book book, @PathVariable Integer bookId){
		return this.bookService.updateBook(book, bookId);
	}
	
	@DeleteMapping("/{bookId}")
	public Mono<Void> deleteBook (@PathVariable Integer bookId){
		 return this.bookService.deleteBook(bookId);
	}
	
	@GetMapping("/searchauthor")
	public Flux<Book> searchBookByAuthorName(@RequestParam("author") String author){
		return this.bookService.searchBook(author);
	}
	
}
