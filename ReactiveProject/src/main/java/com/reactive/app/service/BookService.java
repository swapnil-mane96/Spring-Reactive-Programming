package com.reactive.app.service;

import com.reactive.app.entities.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
	
	Mono<Book> createBook(Book book);
	
	Flux<Book> getAllBooks();
	
	Mono<Book> getSingleBook(Integer bookId);
	
	Mono<Book> updateBook(Book book, Integer bookId);
	
	Mono<Void> deleteBook(Integer bookId);
	
	Flux<Book> searchBook(String query);

}
